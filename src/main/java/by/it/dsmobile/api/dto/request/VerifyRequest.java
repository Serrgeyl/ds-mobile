package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {

    @NotNull
    private String phoneNumber;

}
