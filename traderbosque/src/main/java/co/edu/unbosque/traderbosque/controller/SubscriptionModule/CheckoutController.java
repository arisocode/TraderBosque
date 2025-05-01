package co.edu.unbosque.traderbosque.controller.SubscriptionModule;

import co.edu.unbosque.traderbosque.controller.SubscriptionModule.interfaces.IStripeCheckoutAPI;
import co.edu.unbosque.traderbosque.service.stripe.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckoutController implements IStripeCheckoutAPI {

    private final StripeService stripeService;

    public CheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestParam("lookup_key") String lookupKey) {
        String sessionUrl = stripeService.createCheckoutSession(lookupKey);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", sessionUrl);
        return ResponseEntity.ok(responseData);
    }

    @Override
    public ResponseEntity<Map<String, String>> createPortalSession(@RequestParam String sessionId) {
        String portalUrl = stripeService.createPortalSession(sessionId);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", portalUrl);
        return ResponseEntity.ok(responseData);
    }
}
