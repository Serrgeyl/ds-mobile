package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.ServiceResponse;
import by.it.dsmobile.core.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "price", expression = "java(by.it.dsmobile.core.util.UnitsConverter.parseMoneyUnitsToDouble(service.getPrice()))")
    ServiceResponse toServiceResponse(Service service);

}
