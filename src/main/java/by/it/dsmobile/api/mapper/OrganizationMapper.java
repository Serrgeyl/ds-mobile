package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.OrganizationResponse;
import by.it.dsmobile.core.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationResponse toOrganizationResponse(Organization organization) {
        if (organization == null) {
            return null;
        }

        final var organizationResponse = new OrganizationResponse();

        organizationResponse.setId(organization.getId());
        organizationResponse.setName(organization.getName());

        final var city = organization.getCity();
        if (city != null) {
            organizationResponse.setCity(city.getName());
        }

        return organizationResponse;
    }

}
