package by.it.dsmobile.api.dto.request;

import by.it.dsmobile.core.model.PlatformType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String phoneNumber;

    @NotNull
    private String code;

    @NotNull
    private String pushToken;

    @NotNull
    private PlatformType platform;

}
