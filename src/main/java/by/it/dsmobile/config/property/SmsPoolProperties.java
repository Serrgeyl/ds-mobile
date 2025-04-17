package by.it.dsmobile.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "sms-service.pool")
@Component
public class SmsPoolProperties {

    private int max;
    private int min;
    private int lifetime;

}
