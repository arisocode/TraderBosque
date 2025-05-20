package co.edu.unbosque.traderbosque.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alpaca.api")
@Getter
@Setter
public class AlpacaProperties {
    private String key;
    private String secret;
    private String baseUrl;
}
