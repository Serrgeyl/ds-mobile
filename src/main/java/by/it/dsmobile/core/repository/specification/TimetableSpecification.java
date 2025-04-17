package by.it.dsmobile.core.repository.specification;

import by.it.dsmobile.core.model.Timetable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimetableSpecification {

    public static Specification<Timetable> hasOrganization(Integer organizationId) {
        return organizationId == null
                ? (root, query, builder) -> builder.conjunction()
                : (root, query, builder) -> builder.equal(root.get("organization").get("id"), organizationId);
    }

    public static Specification<Timetable> hasGroup(Integer groupId) {
        return groupId == null
                ? (root, query, builder) -> builder.conjunction()
                : (root, query, builder) -> builder.equal(root.get("group").get("id"), groupId);
    }

    public static Specification<Timetable> hasWeekday(Integer weekdayId) {
        return weekdayId == null
                ? (root, query, builder) -> builder.conjunction()
                : (root, query, builder) -> builder.equal(root.get("weekday"), weekdayId);
    }

    public static Specification<Timetable> hasTeacher(Integer teacherId) {
        return teacherId == null
                ? (root, query, builder) -> builder.conjunction()
                : (root, query, builder) -> builder.equal(root.get("teacher").get("id"), teacherId);
    }

}
