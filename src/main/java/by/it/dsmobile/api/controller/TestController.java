package by.it.dsmobile.api.controller;

import by.it.dsmobile.api.dto.request.TestSms;
import by.it.dsmobile.core.service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/date")
    public boolean isBefore(final @RequestParam LocalDate date) {
        final var start = LocalDate.parse("2025-09-01");
        return date.isBefore(start);
    }

}
