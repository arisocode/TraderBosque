package co.edu.unbosque.traderbosque.service.stripe;

import co.edu.unbosque.traderbosque.model.DTO.SubscriptionDTO;
import co.edu.unbosque.traderbosque.model.entity.SubscriptionPersonalized;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.model.entity.Wallet;
import co.edu.unbosque.traderbosque.repository.SubscriptionRepository;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.repository.WalletRepository;
import co.edu.unbosque.traderbosque.service.alpaca.implementation.UserService;
import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StripeService {

    @Autowired
    private SubscriptionRepository subscriptionReposiroty;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    final String DOMAIN = "http://localhost:3000/account/products";

    public String createCheckoutSession(String lookupKey, String username) {
        try {
            PriceListParams priceParams = PriceListParams.builder()
                    .addLookupKeys(lookupKey)
                    .build();

            PriceCollection prices = Price.list(priceParams);

            SessionCreateParams params = SessionCreateParams.builder()
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setPrice(prices.getData().get(0).getId())
                            .setQuantity(1L)
                            .build())
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setSuccessUrl(DOMAIN + "?success=true&session_id={CHECKOUT_SESSION_ID}&username=" + username)
                    .setCancelUrl(DOMAIN + "?canceled=true")
                    .build();

            Session session = Session.create(params);
            return session.getUrl();

        }catch(StripeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String createPortalSession(String sessionId) {
        try{
            Session checkoutSession = Session.retrieve(sessionId);

            com.stripe.param.billingportal.SessionCreateParams params = new com.stripe.param.billingportal.SessionCreateParams.Builder()
                    .setReturnUrl(DOMAIN).setCustomer(checkoutSession.getCustomer()).build();

            com.stripe.model.billingportal.Session portalSession = com.stripe.model.billingportal.Session.create(params);

            return portalSession.getUrl();
        } catch (StripeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void handleEvent(String payload, String sigHeader, String endpointSecret) {
        try{
            Event event = null;

            try {
                event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            } catch (JsonSyntaxException | SignatureVerificationException e) {
                System.out.println("⚠️  Error procesando el webhook: " + e.getMessage());
                return;
            }

            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = dataObjectDeserializer.getObject().orElse(null);
            //User user = userService.readUsername(username);

            if (stripeObject == null) {
                System.out.println("⚠️  No se pudo deserializar el objeto del evento.");
                return;
            }



            switch (event.getType()) {
                case "customer.subscription.created":
                    handleSubscriptionCreated((com.stripe.model.Subscription) stripeObject);
                    break;
                case "customer.subscription.updated":
                    com.stripe.model.Subscription subscription = (com.stripe.model.Subscription) stripeObject;
                    handleSubscriptionUpdated((com.stripe.model.Subscription) stripeObject);
                    break;
                case "customer.subscription.deleted":
                    handleSubscriptionDeleted((com.stripe.model.Subscription) stripeObject);
                    break;
                case "checkout.session.completed":
                    handleCheckoutSessionCompleted((Session) stripeObject);
                    break;
                default:
                    System.out.println("Evento no manejado: " + event.getType());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /*
    * Consigue la información almacenada de la suscripcion
    * */
     public SubscriptionPersonalized getSubscriptionStatus(String username) {
        List<SubscriptionPersonalized>  subs  = subscriptionReposiroty.findAll();
        List<User> users = userRepository.findAll();

        for(User user : users){
            //Comprueba los usuarios
            if(username.equals(user.getUserName())){
                for(SubscriptionPersonalized sub : subs){
                    //Comprueba los ids para retornar la suscripcion
                    if(sub.getSubId().equals(user.getSubscriptionPersonalized().getSubId())){
                        return sub;
                    }
                }
            }
        }

        return null;
     }



    /*
    * Crea una suscripcion
    * */
    public int handleSubscriptionCreated(SubscriptionDTO dto) {
        try{
            Optional<User> found = userRepository.findByUserName(dto.getUsername());
            User user = found.orElse(null);

            SubscriptionPersonalized subscription = new SubscriptionPersonalized();
            subscription.setSubId(dto.getSubId());
            subscription.setStatus(dto.getStatus());
            subscription.setSubDateStart(dto.getSubDateStart());
            subscription.setSubDateEnd(dto.getSubDateEnd());
            subscription.setUser(user);

            Customer stripeCustomer = Customer.create(CustomerCreateParams.builder()
                    .setEmail(user.getEmail())
                    .setName(user.getName())
                    .build());

            subscription.setStripeCustomerId(stripeCustomer.getId());
            user.setSubscriptionPersonalized(subscription);
            userRepository.save(user);
            subscriptionReposiroty.save(subscription);
            return 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    /*
    * Para crear en el handleEvent
    * */
    public void handleSubscriptionCreated(com.stripe.model.Subscription sub) {
        //Logica de .created

    }
    /*
    * Actualiza la información de la suscripción
    * */
    public void handleSubscriptionUpdated(com.stripe.model.Subscription subscription, User user) {
        //SubscriptionPersonalized subStatus = status;
        //subStatus.setStatus(subscription.getStatus());

        //subscriptionReposiroty.save(subStatus);
    }

    /*
    * Para el handleEvent
    * */
    public void handleSubscriptionUpdated(com.stripe.model.Subscription subscription) {
        //SubscriptionPersonalized subStatus = status;
        //subStatus.setStatus(subscription.getStatus());

        //subscriptionReposiroty.save(subStatus);
    }

    public void handleSubscriptionDeleted(com.stripe.model.Subscription subscription) {
        System.out.println("❌ Suscripción eliminada: " + subscription.getId());
    }


    /*
    * Una fucnión que maneja la sesión de pago de la billetera de Stripe
    * */
    public String createWalletSession(Long amount, String username) {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(DOMAIN + "/wallet/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl(DOMAIN + "/wallet/cancel")
                    .setClientReferenceId(username)
                    .putMetadata("type", "wallet")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount(amount)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Recarga de billetera")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            Session session = Session.create(params);
            return session.getUrl();
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * Funcionque maneja el WebHook del link de pago de la billetera
    * */
    public void handleCheckoutSessionCompleted(Session session) {
        String username = session.getClientReferenceId();
        Long amount = session.getAmountTotal();

        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Wallet wallet = user.getWallet();
            if (wallet == null) {
                wallet = new Wallet();
                wallet.setUser(user);
                wallet.setBalance(0L);
            }
            System.out.println("Pues bro, llego aca");
            wallet.setBalance(wallet.getBalance() + amount);
            walletRepository.save(wallet);
        }
    }
}
