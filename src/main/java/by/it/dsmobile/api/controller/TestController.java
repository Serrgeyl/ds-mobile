package by.it.dsmobile.api.controller;

import by.it.dsmobile.api.dto.request.TestSms;
import by.it.dsmobile.core.service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private final SmsService smsService;

    @PostMapping("/sms")
    public String sendSms(final @RequestBody TestSms testSms) {
        try {
            smsService.send(testSms.getPhoneNumber(), testSms.getMessage());
            return "OK";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
