package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
* Clase encargada de realizar pruebas basicas.
*
* @author Mathew Garzon
* */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testValidateCredentials_Success() {
        User user = new User();
        user.setUserName("juan");
        user.setPassword("1234");

        when(userRepository.findAll()).thenReturn(List.of(user));

        int result = userService.validateCredentials("juan", "1234");

        assertEquals(0, result);
    }

    @Test
    void testValidateCredentials_Fail() {
        User user = new User();
        user.setUserName("juan");
        user.setPassword("1234");

        when(userRepository.findAll()).thenReturn(List.of(user));

        int result = userService.validateCredentials("juan", "wrongpass");

        assertEquals(1, result);
    }

    @Test
    void testUpdatePasswordOnly_Success() {
        User user = new User();
        user.setUserName("maria");
        user.setPassword("oldpass");

        when(userRepository.findAll()).thenReturn(List.of(user));

        ChangePasswordRequestDTO dto = new ChangePasswordRequestDTO();
        dto.setUsername("maria");
        dto.setOldPassword("oldpass");
        dto.setNewPassword("newpass");

        int result = userService.updatePasswordOnly(dto);

        assertEquals(0, result);
        verify(userRepository).save(user);
        assertEquals("newpass", user.getPassword());
    }
}
