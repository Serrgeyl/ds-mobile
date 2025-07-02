package by.it.dsmobile.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TimetableTeacherResponse {

    private Integer number;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime start;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime end;

    private String subject;

    private String className;

}
