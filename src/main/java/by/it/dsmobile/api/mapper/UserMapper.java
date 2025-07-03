package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.UserBriefResponse;
import by.it.dsmobile.api.dto.response.UserLogin;
import by.it.dsmobile.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "type", source = "userType")
    UserLogin toUserLogin(final User user);

    UserBriefResponse toUserBriefResponse(final User user);

}
