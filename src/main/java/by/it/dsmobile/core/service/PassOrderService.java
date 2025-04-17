package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.PassOrderRequest;
import by.it.dsmobile.core.exception.ConflictException;
import by.it.dsmobile.core.model.PassOrderReason;
import by.it.dsmobile.core.model.PassOrderStatus;
import by.it.dsmobile.core.model.PassOrder;
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

    @Transactional
    public void create(PassOrderRequest passOrderRequest) {
        checkOrderForDuplicatesByUserId(passOrderRequest.getUserId());

        final var user = userService.findById(passOrderRequest.getUserId());
        final var owner = userService.findById(passOrderRequest.getOwnerId());
        final var group = user.getGroups().stream().findFirst().orElse(null);

        final var passOrder = new PassOrder();
        passOrder.setUser(user);
        passOrder.setOwner(owner);
        passOrder.setGroup(group);
        passOrder.setReason(PassOrderReason.LOST);
        passOrder.setStatus(PassOrderStatus.NON_PAID);
        passOrder.setMobileRequest(true);

        passOrderRepository.save(passOrder);
    }

    private void checkOrderForDuplicatesByUserId(Integer id) {
        long uncompletedOrdersCount = passOrderRepository.countByUserIdAndStatusIsNot(id, PassOrderStatus.COMPLETED);
        if (uncompletedOrdersCount > 0) {
            throw new ConflictException("Multiple card orders found for user [id = %s]".formatted(id));
        }

        passOrderRepository
                .findByUserIdAndStatus(id, PassOrderStatus.COMPLETED)
                .forEach(passOrder -> {
                    if (Duration.between(passOrder.getUpdatedAt(), OffsetDateTime.now()).toDays() < DAYS_NUMBER_TO_AVOID_NEW_PASS_ORDERS) {
                        throw new ConflictException("Less than %s days after pass order was completed for user [id = %s]"
                                .formatted(DAYS_NUMBER_TO_AVOID_NEW_PASS_ORDERS, id));
                    }
                });
    }

}
