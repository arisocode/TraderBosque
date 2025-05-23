package co.edu.unbosque.traderbosque.service.stripe;

import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.model.entity.Wallet;
import co.edu.unbosque.traderbosque.repository.SubscriptionRepository;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.repository.WalletRepository;
import co.edu.unbosque.traderbosque.service.alpaca.implementation.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StripeServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private StripeService stripeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Mock estático para Price.list()
    @Test
    void createCheckoutSession_Success() throws StripeException {
        // Creamos el mock Price con ID
        Price mockPrice = mock(Price.class);
        when(mockPrice.getId()).thenReturn("price_123");

        // Creamos mock PriceCollection con lista que contiene mockPrice
        PriceCollection mockPriceCollection = mock(PriceCollection.class);
        when(mockPriceCollection.getData()).thenReturn(java.util.List.of(mockPrice));

        // Mock estático para Price.list()
        try (MockedStatic<Price> priceStaticMock = mockStatic(Price.class)) {
            priceStaticMock.when(() -> Price.list((PriceListParams) any())).thenReturn(mockPriceCollection);

            // Mock estático para Session.create()
            try (MockedStatic<Session> sessionStaticMock = mockStatic(Session.class)) {
                Session mockSession = mock(Session.class);
                when(mockSession.getUrl()).thenReturn("http://dummy.url/session");

                sessionStaticMock.when(() -> Session.create(any(SessionCreateParams.class))).thenReturn(mockSession);

                // Llamada al método bajo prueba
                String url = stripeService.createCheckoutSession("lookup_key_dummy", "testuser");

                assertEquals("http://dummy.url/session", url);
            }
        }
    }

    // Test para createWalletSession()
    @Test
    void createWalletSession_Success() throws StripeException {
        try (MockedStatic<Session> sessionStaticMock = mockStatic(Session.class)) {
            Session mockSession = mock(Session.class);
            when(mockSession.getUrl()).thenReturn("http://dummy.url/walletsession");

            sessionStaticMock.when(() -> Session.create(any(SessionCreateParams.class))).thenReturn(mockSession);

            String url = stripeService.createWalletSession(5000L, "testuser");

            assertEquals("http://dummy.url/walletsession", url);
        }
    }

    // Test para handleCheckoutSessionCompleted()
    @Test
    void handleCheckoutSessionCompleted_UserWithWallet() {
        String username = "testuser";

        User user = new User();
        user.setUserName(username);
        Wallet wallet = new Wallet();
        wallet.setBalance(1000L);
        wallet.setUser(user);
        user.setWallet(wallet);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        Session session = mock(Session.class);
        when(session.getClientReferenceId()).thenReturn(username);
        when(session.getAmountTotal()).thenReturn(5000L);

        stripeService.handleCheckoutSessionCompleted(session);

        // Verifica que el balance se haya actualizado correctamente
        assertEquals(6000L, user.getWallet().getBalance());
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void handleCheckoutSessionCompleted_UserWithoutWallet() {
        // Arrange
        String username = "testuser";
        Long amount = 1000L;

        User user = new User();
        user.setUserName(username);
        user.setWallet(null); // Sin wallet para forzar creación

        Session mockSession = Mockito.mock(Session.class);
        Mockito.when(mockSession.getClientReferenceId()).thenReturn(username);
        Mockito.when(mockSession.getAmountTotal()).thenReturn(amount);

        Mockito.when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        Mockito.doAnswer(invocation -> {
            Wallet wallet = invocation.getArgument(0);
            user.setWallet(wallet);  // Actualiza el usuario con la wallet guardada
            return wallet;
        }).when(walletRepository).save(Mockito.any(Wallet.class));

        // Act
        stripeService.handleCheckoutSessionCompleted(mockSession);

        // Assert
        assertNotNull(user.getWallet(), "Wallet debe ser creada y asignada al usuario");
        assertEquals(amount, user.getWallet().getBalance());
    }
}

