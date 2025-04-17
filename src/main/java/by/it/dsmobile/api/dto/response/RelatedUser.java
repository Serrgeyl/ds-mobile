package by.it.dsmobile.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatedUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private ServiceResponse service;
    private GroupResponse group;

}
