package co.edu.unbosque.traderbosque.controller;

import co.edu.unbosque.traderbosque.controller.alpaca.UserModule.UserController;
import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private IService<AccountDTO, Integer> service;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreated() throws Exception {
        AccountDTO dto = new AccountDTO();

        AlpacaAccountResponseDTO alpacaResponse = new AlpacaAccountResponseDTO();
        when(service.create(dto)).thenReturn(alpacaResponse);

        ResponseEntity<?> response = userController.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).create(dto);
    }

    @Test
    void read_ShouldReturnAccountDTO() {
        AccountDTO dto = new AccountDTO();
        when(service.read(1)).thenReturn(Optional.of(dto));

        ResponseEntity<?> response = userController.read(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Aquí comparamos con Optional.of(dto)
        assertEquals(Optional.of(dto), response.getBody());
        verify(service, atLeastOnce()).read(1);
    }


    @Test
    void update_ShouldReturnUpdatedDTO() {
        AccountDTO dto = new AccountDTO();
        doNothing().when(service).update(1, dto);

        ResponseEntity<?> response = userController.update(1, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service).update(1, dto);
    }

    @Test
    void updatePasswordOnly_ShouldReturnAccepted_WhenStatusZero() {
        ChangePasswordRequestDTO changeDto = new ChangePasswordRequestDTO();
        when(service.updatePasswordOnly(changeDto)).thenReturn(0);

        ResponseEntity<?> response = userController.updatePasswordOnly(changeDto);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(changeDto, response.getBody());
        verify(service).updatePasswordOnly(changeDto);
    }

    @Test
    void updatePasswordOnly_ShouldReturnUnauthorized_WhenStatusNonZero() {
        ChangePasswordRequestDTO changeDto = new ChangePasswordRequestDTO();
        when(service.updatePasswordOnly(changeDto)).thenReturn(1);

        ResponseEntity<?> response = userController.updatePasswordOnly(changeDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Password incorrect", response.getBody());
        verify(service).updatePasswordOnly(changeDto);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(service).delete(1);

        ResponseEntity<?> response = userController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(service).delete(1);
    }

    @Test
    void readAll_ShouldReturnList() {
        List<AccountDTO> list = List.of(new AccountDTO());
        when(service.readAll()).thenReturn(list);

        ResponseEntity<?> response = userController.readAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(service).readAll();
    }

    @Test
    void readAllUsers_ShouldReturnList() {
        User list = new User();
        when(service.readAllUsers(1)).thenReturn(list);

        ResponseEntity<?> response = userController.readAllUsers(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(service).readAllUsers(1);
    }

    @Test
    void readUserByName_ShouldReturnUser() {
        User dto = new User();
        when(service.readUsername("user1")).thenReturn(dto);

        ResponseEntity<?> response = userController.readUserByName("user1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service).readUsername("user1");
    }

    @Test
    void checkLogin_ShouldReturnAccepted_WhenStatusZero() {
        User user = new User();
        user.setUserName("user1");
        user.setPassword("pass");

        User finishedUser = new User();
        finishedUser.setUserName("user1");

        when(service.validateCredentials("user1", "pass")).thenReturn(0);
        when(service.readUsername("user1")).thenReturn(finishedUser);

        HttpSession session = mock(HttpSession.class);

        ResponseEntity<?> response = userController.checkLogin(user, session);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(service).validateCredentials("user1", "pass");
        verify(service).readUsername("user1");
        verify(session).setAttribute("user", finishedUser);
    }


    @Test
    void checkLogin_ShouldReturnAccepted_WhenCredentialsAreValid() {
        User user = new User();
        user.setUserName("user1");
        user.setPassword("correctpass");

        User finishedUser = new User();
        finishedUser.setUserName("user1");

        when(service.validateCredentials("user1", "correctpass")).thenReturn(0);
        when(service.readUsername("user1")).thenReturn(finishedUser);

        HttpSession session = mock(HttpSession.class); // Mock de la sesión

        // Act
        ResponseEntity<?> response = userController.checkLogin(user, session);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(service).validateCredentials("user1", "correctpass");
        verify(service).readUsername("user1");
        verify(session).setAttribute("user", finishedUser);
    }

}

