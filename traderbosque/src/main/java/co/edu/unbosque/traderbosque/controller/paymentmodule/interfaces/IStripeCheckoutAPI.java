package co.edu.unbosque.traderbosque.controller.paymentmodule.interfaces;

import co.edu.unbosque.traderbosque.model.SubscriptionDTO;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/checkout")
public interface IStripeCheckoutAPI {

    @PostMapping("/create-checkout-session")
    ResponseEntity<Map<String, String>> createCheckoutSession(@RequestParam String lookupKey, @RequestAttribute  String username);

    @PostMapping("/create-portal-session")
    ResponseEntity<Map<String, String>> createPortalSession(@RequestParam String sessionId);

    @GetMapping("/get-sub-status")
    ResponseEntity<?> getSubStatus(@RequestParam String username);

    @GetMapping("/session/{id}")
    ResponseEntity<?> getSessionDetails(@PathVariable("id") String sessionId) throws StripeException;

    @PostMapping("/subscription/save")
    ResponseEntity<?> saveSubscription(@RequestBody SubscriptionDTO dto);

    @PostMapping("/create-wallet-session")
    ResponseEntity<?> createWalletSession(@RequestParam Long amount, @RequestParam String username);
}
