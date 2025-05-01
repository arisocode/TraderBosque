package co.edu.unbosque.traderbosque.controller.SubscriptionModule.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/api/checkout")
public interface IStripeCheckoutAPI {

    @PostMapping("/create-checkout-session")
    ResponseEntity<Map<String, String>> createCheckoutSession(@RequestParam String lookupKey);

    @PostMapping("/create-portal-session")
    ResponseEntity<Map<String, String>> createPortalSession(@RequestParam String sessionId);
}
