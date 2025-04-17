package by.it.dsmobile.api.dto.request;

import by.it.dsmobile.core.model.Weekday;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableRequest {

    private Integer organizationId;
    private Integer groupId;
    private Weekday weekday;
    private Integer timeslotId;
    private Integer subjectId;
    private Integer teacherId;

}
