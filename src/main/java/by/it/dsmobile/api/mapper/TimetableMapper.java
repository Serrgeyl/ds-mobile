package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.TimetableResponse;
import by.it.dsmobile.core.model.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimetableMapper {

    @Mapping(target = "number", source = "timetable.timeslot.lessonNumber")
    @Mapping(target = "start", source = "timetable.timeslot.startTime")
    @Mapping(target = "end", source = "timetable.timeslot.endTime")
    @Mapping(target = "subject", source = "timetable.subject.name")
    TimetableResponse ToTimetableResponse(Timetable timetable);

}
