package by.it.dsmobile.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableSearchCriteria {

    @NotNull
    private Integer organizationId;

    @NotNull
    private Integer weekdayId;

    private Integer groupId;

    private Integer teacherId;

}
