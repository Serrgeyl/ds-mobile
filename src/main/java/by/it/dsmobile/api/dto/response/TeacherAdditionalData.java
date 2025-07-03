package by.it.dsmobile.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherAdditionalData {

    private OrganizationResponse organization;
    private List<GroupBriefResponse> relatedGroups;

}
