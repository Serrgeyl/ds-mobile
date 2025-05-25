package by.it.dsmobile.config.security;

import by.it.dsmobile.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String phone) throws UsernameNotFoundException {
        final var user = userRepository
                .findByPhoneNumberAndSecurityCodeExpirationDateIsAfter(phone.trim(), OffsetDateTime.now())
                .orElseThrow(() -> new UsernameNotFoundException("User not found or secure code expired [phone number = %s]".formatted(phone)));

        return new User(
                user.getPhoneNumber(),
                user.getSecurityCode(),
                List.of()
        );
    }

    public UserDetails findUserByUsername(final String phone) throws UsernameNotFoundException {
        final var user = userRepository
                .findByPhoneNumber(phone.trim())
                .orElseThrow(() -> new UsernameNotFoundException("User not found [phone number = %s]".formatted(phone)));

        return new User(
                user.getPhoneNumber(),
                user.getSecurityCode(),
                List.of()
        );
    }

}
