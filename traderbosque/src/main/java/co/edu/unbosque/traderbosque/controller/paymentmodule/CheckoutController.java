package co.edu.unbosque.traderbosque.controller.paymentmodule;

import co.edu.unbosque.traderbosque.controller.paymentmodule.interfaces.IStripeCheckoutAPI;
import co.edu.unbosque.traderbosque.model.SubscriptionDTO;
import co.edu.unbosque.traderbosque.model.entity.SubscriptionPersonalized;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.SubscriptionRepository;
import co.edu.unbosque.traderbosque.service.alpaca.implementation.UserService;
import co.edu.unbosque.traderbosque.service.stripe.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionRetrieveParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestParam("lookup_key") String lookupKey, @RequestParam String username) {
        String sessionUrl = stripeService.createCheckoutSession(lookupKey, username);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", sessionUrl);
        return ResponseEntity.ok(responseData);
    }

    @Override
    public ResponseEntity<Map<String, String>> createPortalSession(@RequestParam String sessionId) {
        String portalUrl = stripeService.createPortalSession(sessionId);
        System.out.println("Sessio id: " + sessionId);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", portalUrl);
        return ResponseEntity.ok(responseData);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSessionDetails(@PathVariable("id") String sessionId) throws StripeException {
        Session session = Session.retrieve(
                sessionId,
                SessionRetrieveParams.builder()
                        .addExpand("subscription")
                        .build(),
                null
        );

        Subscription subscription = (Subscription) session.getSubscriptionObject();

        Map<String, Object> result = new HashMap<>();
        result.put("subscriptionId", subscription.getId());
        result.put("status", subscription.getStatus());
        result.put("startDate", subscription.getStartDate());
        result.put("endDate", subscription.getCurrentPeriodEnd());

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<String> saveSubscription(@RequestBody SubscriptionDTO dto) {
        int status = stripeService.handleSubscriptionCreated(dto);

        if (status == 1) {
            return new ResponseEntity<>("Sub Created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Sub Creation Error",
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public ResponseEntity<?> getSubStatus(@RequestParam String username) {
        SubscriptionPersonalized subscription = stripeService.getSubscriptionStatus(username);
        System.out.println("Estado de la sub: " + subscription.getStatus() + " like para mas");

        if (subscription == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Subscription not found"));
        }


        return ResponseEntity.status(200).body(subscription);
    }
}
