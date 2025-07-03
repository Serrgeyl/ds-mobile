package by.it.dsmobile.api.controller;

import by.it.dsmobile.core.exception.ValueNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.request.LoginRequest;
import by.it.dsmobile.api.dto.request.VerifyRequest;
import by.it.dsmobile.api.dto.response.LoginResponse;
import by.it.dsmobile.core.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequiredArgsConstructor
@Validated
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Идентификация пользователя по номеру телефона")
    public void verify(@Valid @NotNull @RequestBody final VerifyRequest verifyRequest) {
        authService.verify(verifyRequest.getPhoneNumber().trim());
    }

    @PostMapping("/login")
    @Operation(summary = "Аутентификация пользователя")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @ExceptionHandler(ValueNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String authExceptionHandler(ValueNotFoundException  exception) {
        return exception.getMessage();
    }

}
