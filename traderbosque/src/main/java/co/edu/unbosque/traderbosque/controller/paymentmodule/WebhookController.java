package co.edu.unbosque.traderbosque.controller.paymentmodule;

import co.edu.unbosque.traderbosque.controller.paymentmodule.interfaces.IStripeCheckoutAPI;
import co.edu.unbosque.traderbosque.service.stripe.StripeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    @Value("whsec_12345")
    private String endpointSecret;

    private final StripeService stripeService;

    public WebhookController(StripeService stripeService) {
        this.stripeService = stripeService;
     }

    @PostMapping
    public ResponseEntity<String> handleWebhook(HttpServletRequest request, @RequestBody String payload) {
        System.out.println("payload: " + payload);
        String sigHeader = request.getHeader("Stripe-Signature");
        stripeService.handleEvent(payload, sigHeader, endpointSecret);
        return ResponseEntity.ok("");
    }

}

