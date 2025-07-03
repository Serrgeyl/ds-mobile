package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.GroupResponse;
import by.it.dsmobile.api.dto.response.GroupBriefResponse;
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

    public GroupBriefResponse toRelatedGroup(Group group) {
        if (group == null) {
            return null;
        }

        GroupBriefResponse groupBriefResponse = new GroupBriefResponse();
        groupBriefResponse.setId(group.getId());
        groupBriefResponse.setName(group.getName());
        groupBriefResponse.setMapping(group.getMapping());
        return groupBriefResponse;
    }

}
