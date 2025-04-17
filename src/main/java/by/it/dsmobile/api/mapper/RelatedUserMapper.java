package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.GroupResponse;
import by.it.dsmobile.api.dto.response.RelatedUser;
import by.it.dsmobile.core.model.ServiceToUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RelatedUserMapper {

    private final GroupMapper groupMapper;
    private final ServiceMapper serviceMapper;

    public RelatedUser toRelatedUser(final ServiceToUser serviceToUser) {
        final var relatedUser = new RelatedUser();
        final var user = serviceToUser.getUser();
        final GroupResponse group;
        if (user != null) {
            relatedUser.setId(user.getId());
            relatedUser.setFirstName(user.getFirstName());
            relatedUser.setLastName(user.getLastName());
            group = user.getGroups().stream().findFirst().map(groupMapper::toGroupResponse).orElse(null);
            relatedUser.setGroup(group);
        }

        final var service = serviceToUser.getService();
        if (service != null) {
            relatedUser.setService(serviceMapper.toServiceResponse(service));
        }

        return relatedUser;
    }

}
