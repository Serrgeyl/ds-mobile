package by.it.dsmobile.core.service;

import by.it.dsmobile.config.property.SmsServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SmsService {

    private final static int SMS_PRIORITY_LOW = 0;
    private final static int SMS_PRIORITY_NORMAL = 1;
    private final static int SMS_PRIORITY_IMPORTANT = 2;
    private final static int SMS_PRIORITY_CRITICAL = 3;

    private final String user;
    private final String password;
    private final String sender;
    private final String smsc;
    private final WebClient webClient;

    SmsService(SmsServiceProperties properties, WebClient webClient) {
        this.user = properties.getUser();
        this.password = properties.getPassword();
        this.sender = properties.getSender();
        this.smsc = properties.getSmsc();
        this.webClient = webClient;
    }

    public void send(String phoneNumber, String message) {
        send(phoneNumber, message, SMS_PRIORITY_IMPORTANT);
    }

    public void send(String phoneNumber, String message, int priority) {
        if (phoneNumber.trim().matches("^375[0-9]{9}$")) {
            sendByLife(phoneNumber.trim(), message, priority);
        } else {
            log.error("Invalid phone number: {}", phoneNumber);
        }
    }

    private void sendByLife(final String phoneNumber, final String message, int priority) {
        webClient
                .get()
//                .uri(prepareURI(phoneNumber, message, priority))
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi-bin/sendsms")
                        .queryParam("smsc", smsc)
                        .queryParam("user", user)
                        .queryParam("pass", password)
                        .queryParam("from", sender)
                        .queryParam("charset", "utf-8")
                        .queryParam("coding", 2)
                        .queryParam("to", phoneNumber)
                        .queryParam("text", message)
                        .queryParam("priority", priority)
                        .build())
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(_ -> log.debug("Sms successfully sent to: {}", phoneNumber))
                .doOnError(_ -> log.warn("Failed to send sms to: {}", phoneNumber))
                .subscribe();
    }

    private URI prepareURI(final String phoneNumber, final String message, int priority) {
        return UriComponentsBuilder
                .fromPath("/cgi-bin/sendsms")
                .queryParam("smsc", smsc)
                .queryParam("user", user)
                .queryParam("pass", password)
                .queryParam("from", sender)
                .queryParam("charset", "utf-8")
                .queryParam("coding", 2)
                .queryParam("to", phoneNumber)
                .queryParam("text", message)
                .queryParam("priority", priority)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }

}
