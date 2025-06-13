package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.LoginRequest;
import by.it.dsmobile.api.dto.response.LoginResponse;
import by.it.dsmobile.api.mapper.UserLoginMapper;
import by.it.dsmobile.config.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.OffsetDateTime;

import static by.it.dsmobile.core.util.AppConstants.SECURITY_CODE_EXPIRATION_TIME;

@Service
@AllArgsConstructor
public class AuthService {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PushTokenService pushTokenService;
    private final UserLoginMapper userLoginMapper;

    @Transactional
    public String verify(final String phoneNumber) {
        final var user = userService.findByPhoneNumber(phoneNumber.trim());
        final var code = createSecureCode();
        user.setSecurityCode(passwordEncoder.encode(code));
        user.setSecurityCodeExpirationDate(OffsetDateTime.now().plusMinutes(SECURITY_CODE_EXPIRATION_TIME));
        userService.saveAndFlush(user);
        return code;
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getCode())
        );
        final var user = userService.findByPhoneNumber(loginRequest.getPhoneNumber().trim());
        pushTokenService.save(user, loginRequest.getPushToken(), loginRequest.getPlatform());

        final var userLogin = userLoginMapper.toUserLogin(user);
        final var accessToken = jwtProvider.generateToken(((UserDetails) authenticate.getPrincipal()).getUsername());
        return new LoginResponse(userLogin, accessToken);
    }

    private static String createSecureCode() {
        final var buffer = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            buffer.append(RANDOM.nextInt(10));
        }
        return buffer.toString();
    }
}
