package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.GroupResponse;
import by.it.dsmobile.api.dto.response.RelatedUser;
import by.it.dsmobile.api.dto.response.UserBriefResponse;
import by.it.dsmobile.api.dto.response.UserLogin;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.ServiceToUser;
import by.it.dsmobile.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ServiceMapper.class, GroupMapper.class})
public interface UserMapper {

    GroupMapper GROUP_MAPPER = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "type", source = "userType")
    UserLogin toUserLogin(final User user);

    UserBriefResponse toUserBriefResponse(final User user);

    @Mapping(target = "id", source = "serviceToUser.user.id")
    @Mapping(target = "firstName", source = "serviceToUser.user.firstName")
    @Mapping(target = "lastName", source = "serviceToUser.user.lastName")
    @Mapping(target = "group", expression = "java(getGroup(serviceToUser))")
    RelatedUser toRelatedUser(final ServiceToUser serviceToUser);

    default GroupResponse getGroup(final ServiceToUser serviceToUser) {
        final var user = serviceToUser.getUser();
        return user
                .getGroups()
                .stream()
                .findFirst()
                .map(GROUP_MAPPER::toGroupResponse)
                .orElseThrow(() -> new ValueNotFoundException("Group [type = class] not found for user [id = %s]".formatted(user.getId())));
    }

}
