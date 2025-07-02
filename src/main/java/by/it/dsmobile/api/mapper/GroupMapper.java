package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.GroupResponse;
import by.it.dsmobile.api.dto.response.RelatedGroup;
import by.it.dsmobile.core.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupMapper {

    private final OrganizationMapper organizationMapper;

    GroupResponse toGroupResponse(Group group) {
        if (group == null) {
            return null;
        }

        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setName(group.getName());
        groupResponse.setMapping(group.getMapping());
        groupResponse.setType(group.getGroupType());

        final var organization = group.getOrganization();
        if (organization != null) {
            groupResponse.setOrganization(organizationMapper.toOrganizationResponse(organization));
        }

        return groupResponse;
    }

    public RelatedGroup toRelatedGroup(Group group) {
        if (group == null) {
            return null;
        }

        RelatedGroup relatedGroup = new RelatedGroup();
        relatedGroup.setId(group.getId());
        relatedGroup.setName(group.getName());
        relatedGroup.setMapping(group.getMapping());
        return relatedGroup;
    }

}
