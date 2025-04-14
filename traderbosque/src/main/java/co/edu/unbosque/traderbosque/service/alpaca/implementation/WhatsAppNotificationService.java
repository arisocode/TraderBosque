package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class WhatsAppNotificationService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public void sendWhatsAppMessage(String to, String messageBody) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + to),
                new com.twilio.type.PhoneNumber("whatsapp:" + twilioPhoneNumber),
                messageBody
        ).create();

        System.out.println("Mensaje de WhatsApp enviado con SID: " + message.getSid());
    }
}