package by.it.dsmobile.api.controller;

import by.it.dsmobile.api.dto.request.TestSms;
import by.it.dsmobile.core.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.image-path}")
    private String imagePath;

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

    @GetMapping("/encode")
    public String encode(@RequestParam String code) {
        return passwordEncoder.encode(code);
    }

    @GetMapping("/ok")
    public String ok() {
        return "Ok";
    }

    @PostMapping("/image")
    public void image(final @RequestBody MultipartFile image) {
        Path path = Paths.get(imagePath, image.getOriginalFilename());
        log.info(path.toString());
        try {
            Files.write(path, image.getBytes(), CREATE_NEW);
        } catch (FileAlreadyExistsException e) {
//            System.out.println("Image already exists");
            log.info("Image already exists");
        } catch (IOException e) {
//            System.out.println("Failed to store image");
            log.info("Failed to store image");
        }

//        final var input = new File("/Users/slem/IMG_6257.jpg");
//        final var output = new File("/Users/slem/IMG_6251_o1.jpg");
//
//            Thumbnails
//                    .of(input)
//                    .scale(0.5)
//                    .outputQuality(0.5)
//                    .toFile(output);


    }

}
