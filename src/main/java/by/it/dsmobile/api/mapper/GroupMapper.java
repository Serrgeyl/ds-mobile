package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.GroupBriefResponse;
import by.it.dsmobile.api.dto.response.GroupResponse;
import by.it.dsmobile.core.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrganizationMapper.class})
public interface GroupMapper {

    @Mapping(target = "type", source = "groupType")
    GroupResponse toGroupResponse(Group group);

    GroupBriefResponse toGroupBriefResponse(Group group);

}
