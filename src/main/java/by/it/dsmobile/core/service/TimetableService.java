package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.request.TimetableSearchCriteria;
import by.it.dsmobile.api.dto.request.TimetableTeacherSearchCriteria;
import by.it.dsmobile.api.dto.response.TimetableResponse;
import by.it.dsmobile.api.dto.response.TimetableTeacherResponse;
import by.it.dsmobile.api.mapper.TimetableMapper;
import by.it.dsmobile.core.model.Timetable;
import by.it.dsmobile.core.repository.TimetableRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static by.it.dsmobile.core.repository.specification.TimetableSpecification.*;

@Service
@AllArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;

    public List<TimetableResponse> getTimetable(final TimetableSearchCriteria searchCriteria) {
        final var specification = getSpecification(searchCriteria);
        return timetableRepository
                .findAll(specification)
                .stream()
                .map(timetableMapper::toTimetableResponse)
                .sorted(Comparator.comparingInt(TimetableResponse::getNumber))
                .toList();
    }

    public List<TimetableTeacherResponse> getTeacherTimetable(final TimetableTeacherSearchCriteria searchCriteria) {
        final var specification = getSpecification(searchCriteria);
        return timetableRepository
                .findAll(specification)
                .stream()
                .map(timetableMapper::toTimetableTeacherResponse)
                .sorted(Comparator.comparingInt(TimetableTeacherResponse::getNumber))
                .toList();
    }

    private Specification<Timetable> getSpecification(final TimetableSearchCriteria searchCriteria) {
        return hasOrganization(searchCriteria.getOrganizationId())
                .and(hasGroup(searchCriteria.getGroupId()))
                .and(hasWeekday(searchCriteria.getWeekdayId()));
    }

    private Specification<Timetable> getSpecification(final TimetableTeacherSearchCriteria searchCriteria) {
        return hasOrganization(searchCriteria.getOrganizationId())
                .and(hasWeekday(searchCriteria.getWeekdayId()))
                .and(hasTeacher(searchCriteria.getTeacherId()));
    }

}
