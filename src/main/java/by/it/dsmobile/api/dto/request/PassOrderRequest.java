package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassOrderRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer ownerId;

    @NotNull
    private Integer organizationId;

    private Integer serviceId;

    private Integer groupId;

}
