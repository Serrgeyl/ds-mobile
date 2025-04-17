package by.it.dsmobile.api.dto.response;

import by.it.dsmobile.core.model.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private UserType type;
    private Double balance;  // TODO replace to long

}
