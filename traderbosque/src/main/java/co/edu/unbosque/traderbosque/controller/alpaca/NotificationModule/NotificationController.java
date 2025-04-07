package co.edu.unbosque.traderbosque.controller.alpaca.NotificationModule;

import co.edu.unbosque.traderbosque.model.DTO.NotificationRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.NotificationResponseDTO;
import co.edu.unbosque.traderbosque.service.alpaca.implementation.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@RequestBody NotificationRequestDTO request) {
        notificationService.sendNotification(request);
        return ResponseEntity.ok(new NotificationResponseDTO("SUCCESS", "Notificación enviada correctamente"));
    }
}
