package by.it.dsmobile.core.service;

import by.it.dsmobile.core.model.PlatformType;
import by.it.dsmobile.core.model.PushToken;
import by.it.dsmobile.core.model.User;
import by.it.dsmobile.core.repository.PushTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PushTokenService {

    private final PushTokenRepository pushTokenRepository;

    @Transactional
    public void save(User user, String token, PlatformType platform) {
        final PushToken pushToken;
        final var optionalToken = pushTokenRepository.findFirstByUser(user);
        if (optionalToken.isPresent()) {
            pushToken = optionalToken.get();
        } else {
            pushToken = new PushToken();
            pushToken.setUser(user);
        }
        pushToken.setToken(token);
        pushToken.setPlatform(platform);
        pushTokenRepository.saveAndFlush(pushToken);
    }

}
