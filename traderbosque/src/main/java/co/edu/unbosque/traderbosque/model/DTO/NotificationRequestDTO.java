package co.edu.unbosque.traderbosque.model.DTO;

public class NotificationRequestDTO {
    private Long userId;
    private String email;
    private String phoneNumber;
    private String message;
    private boolean emailEnabled;
    private boolean smsEnabled;
    private boolean whatsappEnabled;

    public NotificationRequestDTO() {}

    public NotificationRequestDTO(Long userId, String email, String phoneNumber, String message,
                                  boolean emailEnabled, boolean smsEnabled, boolean whatsappEnabled) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.whatsappEnabled = whatsappEnabled;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isEmailEnabled() { return emailEnabled; }
    public void setEmailEnabled(boolean emailEnabled) { this.emailEnabled = emailEnabled; }

    public boolean isSmsEnabled() { return smsEnabled; }
    public void setSmsEnabled(boolean smsEnabled) { this.smsEnabled = smsEnabled; }

    public boolean isWhatsappEnabled() { return whatsappEnabled; }
    public void setWhatsappEnabled(boolean whatsappEnabled) { this.whatsappEnabled = whatsappEnabled; }
}