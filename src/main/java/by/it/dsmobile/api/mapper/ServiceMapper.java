package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.ServiceResponse;
import by.it.dsmobile.core.model.Service;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceResponse toServiceResponse(Service service);

}
