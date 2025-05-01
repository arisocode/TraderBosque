package co.edu.unbosque.traderbosque.service.stripe;

import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    final String DOMAIN = "http://localhost:3000/account/products";

    public String createCheckoutSession(String lookupKey) {
        try {
            PriceListParams priceParams = PriceListParams.builder()
                    .addLookupKeys(lookupKey)
                    .build();

            PriceCollection prices = Price.list(priceParams);

            SessionCreateParams params = SessionCreateParams.builder()
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setPrice(prices.getData().get(0).getId())
                            .setQuantity(1L)
                            .build())
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setSuccessUrl(DOMAIN + "?success=true&session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl(DOMAIN + "?canceled=true")
                    .build();

            Session session = Session.create(params);
            return session.getUrl();

        }catch(StripeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String createPortalSession(String sessionId) {
        try{
            Session checkoutSession = Session.retrieve(sessionId);

            com.stripe.param.billingportal.SessionCreateParams params = new com.stripe.param.billingportal.SessionCreateParams.Builder()
                    .setReturnUrl(DOMAIN).setCustomer(checkoutSession.getCustomer()).build();

            com.stripe.model.billingportal.Session portalSession = com.stripe.model.billingportal.Session.create(params);

            return portalSession.getUrl();
        } catch (StripeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void handleEvent(String payload, String sigHeader, String endpointSecret) {
        try{
            Event event = null;
            try {
                event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            } catch (JsonSyntaxException | SignatureVerificationException e) {
                System.out.println("⚠️  Error procesando el webhook: " + e.getMessage());
                return;
            }

            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = dataObjectDeserializer.getObject().orElse(null);

            if (stripeObject == null) {
                System.out.println("⚠️  No se pudo deserializar el objeto del evento.");
                return;
            }

            switch (event.getType()) {
                case "customer.subscription.created":
                    handleSubscriptionCreated((Subscription) stripeObject);
                    break;
                case "customer.subscription.updated":
                    handleSubscriptionUpdated((Subscription) stripeObject);
                    break;
                case "customer.subscription.deleted":
                    handleSubscriptionDeleted((Subscription) stripeObject);
                    break;
                default:
                    System.out.println("Evento no manejado: " + event.getType());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleSubscriptionCreated(Subscription subscription) {
        System.out.println("💡 Suscripción creada: " + subscription.getId());
        // Aquí podrías guardar en BD, enviar correo, etc.
    }

    private void handleSubscriptionUpdated(Subscription subscription) {
        System.out.println("🔁 Suscripción actualizada: " + subscription.getId());
    }

    private void handleSubscriptionDeleted(Subscription subscription) {
        System.out.println("❌ Suscripción eliminada: " + subscription.getId());
    }
}
