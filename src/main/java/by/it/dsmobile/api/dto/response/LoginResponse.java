package by.it.dsmobile.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private UserLogin user;
    private String accessToken;

}
