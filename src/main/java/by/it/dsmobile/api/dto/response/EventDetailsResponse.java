package by.it.dsmobile.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import by.it.dsmobile.core.model.PassType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class EventDetailsResponse {

    PassType passType;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

}
