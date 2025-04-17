package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServiceOrder {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer serviceId;

    @NotNull
    private Integer ownerId;


    private LocalDate sinceDate;  // optional

    @NotNull
    private Boolean mobileRequest;

}
