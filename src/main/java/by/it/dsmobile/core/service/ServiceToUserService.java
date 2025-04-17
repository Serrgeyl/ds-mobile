package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.ServiceOrder;
import by.it.dsmobile.core.exception.ConflictException;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.ServiceToUser;
import by.it.dsmobile.core.repository.ServiceRepository;
import by.it.dsmobile.core.repository.ServiceToUserRepository;
import by.it.dsmobile.core.repository.UserLinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ServiceToUserService {

    private final ServiceToUserRepository serviceToUserRepository;
    private final ServiceRepository serviceRepository;
    private final UserLinkRepository userLinkRepository;

    @Transactional
    public void update(final ServiceOrder serviceOrder) {
        final var serviceToUser = serviceToUserRepository
                .findFirstByOwnerIdAndUserId(serviceOrder.getOwnerId(), serviceOrder.getUserId())
                .orElseThrow(() -> new ValueNotFoundException("No serviceToUser found with owner [id = %s] and user [id = %s]"
                        .formatted(serviceOrder.getOwnerId(), serviceOrder.getUserId())));

        if (Objects.equals(serviceToUser.getService().getId(), serviceOrder.getServiceId())) {
            throw new ConflictException("Multiple service orders found for user [id = %s]".formatted(serviceOrder.getUserId()));
        }

        final var service = serviceRepository.findById(serviceOrder.getServiceId())
                .orElseThrow(() -> new ValueNotFoundException("No service found with id [%s]".formatted(serviceOrder.getServiceId())));
        serviceToUser.setService(service);
        serviceToUser.setMobileRequest(serviceOrder.getMobileRequest());
        final var savedServiceToUser = serviceToUserRepository.save(serviceToUser);

        updateUserLink(savedServiceToUser);
    }

    private void updateUserLink(final ServiceToUser savedServiceToUser) {
        final var userLink = userLinkRepository.findFirstByUserAndOwner(savedServiceToUser.getUser(), savedServiceToUser.getOwner())
                .orElseThrow(() -> new ValueNotFoundException("No userLink found user [id = %s], owner [id = %s]"
                        .formatted(savedServiceToUser.getUser().getId(), savedServiceToUser.getOwner().getId())));

        final var service = savedServiceToUser.getService();
        userLink.setReceivePush(service.getPushNotify());
        userLink.setReceiveSMS(service.getSmsNotify());
        userLinkRepository.save(userLink);
    }

}
