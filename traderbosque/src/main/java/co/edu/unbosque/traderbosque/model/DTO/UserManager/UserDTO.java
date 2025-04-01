package co.edu.unbosque.traderbosque.model.DTO.UserManager;

import java.time.LocalDateTime;

import co.edu.unbosque.traderbosque.model.enums.UserRole;
import co.edu.unbosque.traderbosque.model.enums.UserState;

public class UserDTO extends PersonDTO{

    private String username, password;
    private UserRole userRol;
    private UserState state;
    private LocalDateTime loginDate;

    public UserDTO(String name, String lastName, String nit, String phone, String email, String address,
                    String username, String password, UserRole userRol, UserState state, LocalDateTime loginDate) {
        super(name, lastName, nit, phone, email, address);
        this.username=username;
        this.password=password;
        this.userRol=userRol;
        this.state=state;
        this.loginDate=loginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRol() {
        return userRol;
    }

    public void setUserRol(UserRole userRol) {
        this.userRol = userRol;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }
    
}
