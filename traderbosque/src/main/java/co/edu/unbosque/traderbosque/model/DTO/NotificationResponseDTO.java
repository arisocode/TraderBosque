package co.edu.unbosque.traderbosque.model.DTO;

public class NotificationResponseDTO {
    private String status;
    private String message;

    public NotificationResponseDTO() {}

    public NotificationResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() { return status; }
    public String getMessage() { return message; }
}