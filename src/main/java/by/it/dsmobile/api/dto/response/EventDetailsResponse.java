package by.it.dsmobile.api.dto.response;

import by.it.dsmobile.core.model.EventEntryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class EventDetailsResponse {

    EventEntryType passType; // TODO naming...

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

}
