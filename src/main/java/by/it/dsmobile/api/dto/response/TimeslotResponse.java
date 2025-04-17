package by.it.dsmobile.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TimeslotResponse {

    private Integer lessonNumber;

    private LocalTime startTime;

    private LocalTime endTime;

}
