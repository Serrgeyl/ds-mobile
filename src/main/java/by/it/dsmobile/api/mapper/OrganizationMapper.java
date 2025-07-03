package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.OrganizationResponse;
import by.it.dsmobile.core.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {

    @Mapping(target = "city", source = "organization.city.name")
    OrganizationResponse toOrganizationResponse(Organization organization);

}
