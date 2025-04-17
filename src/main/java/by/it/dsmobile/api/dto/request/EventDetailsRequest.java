package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDetailsRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private LocalDate date;

}
