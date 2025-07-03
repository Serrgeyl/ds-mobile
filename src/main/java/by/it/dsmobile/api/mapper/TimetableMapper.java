package by.it.dsmobile.api.mapper;

import by.it.dsmobile.api.dto.response.TimetableResponse;
import by.it.dsmobile.api.dto.response.TimetableTeacherResponse;
import by.it.dsmobile.core.model.Timetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TimetableMapper {

    @Mapping(target = "number", source = "timetable.timeslot.lessonNumber")
    @Mapping(target = "start", source = "timetable.timeslot.startTime")
    @Mapping(target = "end", source = "timetable.timeslot.endTime")
    @Mapping(target = "subject", source = "timetable.subject.name")
    TimetableResponse toTimetableResponse(Timetable timetable);

    @Mapping(target = "number", source = "timetable.timeslot.lessonNumber")
    @Mapping(target = "start", source = "timetable.timeslot.startTime")
    @Mapping(target = "end", source = "timetable.timeslot.endTime")
    @Mapping(target = "subject", source = "timetable.subject.name")
    @Mapping(target = "className", source = "timetable.group.mapping")
    TimetableTeacherResponse toTimetableTeacherResponse(Timetable timetable);

}
