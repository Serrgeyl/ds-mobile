package by.it.dsmobile.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sms-service.web-client")
public class SmsWebClientProperties {

    private String baseUrl;
    private Integer timeout;

}
