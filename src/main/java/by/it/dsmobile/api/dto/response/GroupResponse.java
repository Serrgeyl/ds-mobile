package by.it.dsmobile.api.dto.response;

import by.it.dsmobile.core.model.GroupType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse {

    private Integer id;
    private String name;
    private String mapping;
    private GroupType type;
    private OrganizationResponse organization;

}
