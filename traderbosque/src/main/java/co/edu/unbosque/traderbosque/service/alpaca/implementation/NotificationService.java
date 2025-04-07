package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.model.DTO.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    //*private final EmailNotificationService emailService;
    private final SMSNotificationService smsService;
    private final WhatsAppNotificationService whatsappService;

    public NotificationService(EmailNotificationService emailService, SMSNotificationService smsService, WhatsAppNotificationService whatsappService) {
        //*this.emailService = emailService;
        this.smsService = smsService;
        this.whatsappService = whatsappService;
    }

    public void sendNotification(NotificationRequestDTO request) {
        //*if (request.isEmailEnabled()) {
            //*emailService.sendEmail(request.getEmail(), request.getMessage());
       //* }
        if (request.isSmsEnabled()) {
            smsService.sendSms(request.getPhoneNumber(), request.getMessage());
        }
        if (request.isWhatsappEnabled()) {
            whatsappService.sendWhatsApp(request.getPhoneNumber(), request.getMessage());
        }
    }
}
