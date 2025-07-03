package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.ServiceResponse;
import by.it.dsmobile.core.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {

    @Mapping(target = "price", expression = "java(by.it.dsmobile.core.util.UnitsConverter.parseMoneyUnitsToDouble(service.getPrice()))")
    ServiceResponse toServiceResponse(Service service);

}
