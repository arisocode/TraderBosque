package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppNotificationService {

    private static final String TWILIO_SID = "";
    private static final String TWILIO_AUTH_TOKEN = "";
    private static final String TWILIO_WHATSAPP = "";

    public void sendWhatsApp(String phoneNumber, String message) {
        Twilio.init(TWILIO_SID, TWILIO_AUTH_TOKEN);
        Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:" + phoneNumber),
            new com.twilio.type.PhoneNumber(TWILIO_WHATSAPP),
            message
        ).create();
    }
}