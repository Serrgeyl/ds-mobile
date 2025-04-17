package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.UserLogin;
import by.it.dsmobile.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLoginMapper {

    @Mapping(target = "type", source = "userType")
    UserLogin toUserLogin(final User user);

}
