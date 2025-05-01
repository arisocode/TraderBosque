package co.edu.unbosque.traderbosque;



import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraderbosqueApplication {

	@Value("${stripe.api.key}")
	private String stripeApiKey;
	public static void main(String[] args) {
		SpringApplication.run(TraderbosqueApplication.class, args);
	}

	@PostConstruct
	public void setup() {
		Stripe.apiKey = stripeApiKey;
	}
}
