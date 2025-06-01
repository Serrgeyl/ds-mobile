package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.PassOrderRequest;
import by.it.dsmobile.core.exception.ConflictException;
import by.it.dsmobile.core.model.Organization;
import by.it.dsmobile.core.model.PassOrder;
import by.it.dsmobile.core.model.PassOrderStatusType;
import by.it.dsmobile.core.model.PassOrderType;
import by.it.dsmobile.core.repository.PassOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PassOrderService {

    private static final long DAYS_NUMBER_TO_AVOID_NEW_PASS_ORDERS = 20;

    private final PassOrderRepository passOrderRepository;
    private final UserService userService;
    private final OrganizationService organizationService;

    @Transactional
    public void create(PassOrderRequest passOrderRequest) {
        checkOrderForDuplicatesByUserId(passOrderRequest.getUserId());

        final var user = userService.findById(passOrderRequest.getUserId());
        final var owner = userService.findById(passOrderRequest.getOwnerId());
        final var organization = organizationService.findById(passOrderRequest.getOrganizationId());

        final var passOrder = new PassOrder();
        passOrder.setUser(user);
        passOrder.setOwner(owner);
        passOrder.setOrganization(organization);
        passOrder.setPassOrderType(PassOrderType.REPEATEDLY);
        passOrder.setPassOrderStatusType(PassOrderStatusType.NEW);
        passOrder.setMobileRequest(true);

        passOrderRepository.save(passOrder);
    }

    private void checkOrderForDuplicatesByUserId(Integer id) {
        long uncompletedOrdersCount = passOrderRepository.countByUserIdAndPassOrderStatusTypeIsNot(id, PassOrderStatusType.COMPLETED);
        if (uncompletedOrdersCount > 0) {
            throw new ConflictException("Multiple card orders found for user [id = %s]".formatted(id));
        }

        passOrderRepository
                .findByUserIdAndPassOrderStatusType(id, PassOrderStatusType.COMPLETED)
                .forEach(passOrder -> {
                    if (Duration.between(passOrder.getUpdatedAt(), OffsetDateTime.now()).toDays() < DAYS_NUMBER_TO_AVOID_NEW_PASS_ORDERS) {
                        throw new ConflictException("Less than %s days after pass order was completed for user [id = %s]"
                                .formatted(DAYS_NUMBER_TO_AVOID_NEW_PASS_ORDERS, id));
                    }
                });
    }

}
