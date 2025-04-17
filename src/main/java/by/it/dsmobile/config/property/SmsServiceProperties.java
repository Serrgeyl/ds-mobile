package by.it.dsmobile.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sms-service")
public class SmsServiceProperties {

    private String user;
    private String password;
    private String sender;
    private String smsc;

}
