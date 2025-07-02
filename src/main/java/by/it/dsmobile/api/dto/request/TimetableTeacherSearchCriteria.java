package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableTeacherSearchCriteria {

    @NotNull
    private Integer organizationId;

    @NotNull
    private Integer weekdayId;

    @NotNull
    private Integer teacherId;

}
