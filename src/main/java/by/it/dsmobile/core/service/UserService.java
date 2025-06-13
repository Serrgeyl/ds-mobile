package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.response.RelatedUser;
import by.it.dsmobile.api.mapper.RelatedUserMapper;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.User;
import by.it.dsmobile.core.repository.ServiceToUserRepository;
import by.it.dsmobile.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ServiceToUserRepository serviceToUserRepository;
    private final RelatedUserMapper relatedUserMapper;


    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ValueNotFoundException("User not found [phoneNumber = %s]".formatted(phoneNumber)));
    }

    public User findById(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("No user found [id = %s]".formatted(id)));
    }

    public void saveAndFlush(final User user) {
        userRepository.saveAndFlush(user);
    }

    public List<RelatedUser> getRelatedUsers(final Integer id) {
        return serviceToUserRepository
                .findByOwnerId(id)
                .stream()
                .filter(s -> !s.getService().getDisposable())
                .map(relatedUserMapper::toRelatedUser)
                .sorted(Comparator.comparingInt(RelatedUser::getId))
                .toList();
    }

    public Double getBalanceById(final Integer id) {
        return userRepository.getBalanceById(id)
                .orElseThrow(() -> new ValueNotFoundException("User not found [id = %s]".formatted(id)));
    }

}
