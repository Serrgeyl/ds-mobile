package by.it.dsmobile.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdministrationAdditionalData {

    private List<UserBriefResponse> teachers;
    private List<GroupBriefResponse> groups;

}
