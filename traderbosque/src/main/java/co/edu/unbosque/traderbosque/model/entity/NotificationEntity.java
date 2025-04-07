package co.edu.unbosque.traderbosque.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String email;
    private String phoneNumber;
    private String message;
    private boolean emailEnabled;
    private boolean smsEnabled;
    private boolean whatsappEnabled;

    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;

    public NotificationEntity() {
        this.sentAt = LocalDateTime.now();
    }

    public NotificationEntity(Long userId, String email, String phoneNumber, String message,
                              boolean emailEnabled, boolean smsEnabled, boolean whatsappEnabled) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.whatsappEnabled = whatsappEnabled;
        this.sentAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getMessage() { return message; }
    public boolean isEmailEnabled() { return emailEnabled; }
    public boolean isSmsEnabled() { return smsEnabled; }
    public boolean isWhatsappEnabled() { return whatsappEnabled; }
    public LocalDateTime getSentAt() { return sentAt; }
}