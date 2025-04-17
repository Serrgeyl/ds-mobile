package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer>, JpaSpecificationExecutor<Timetable> {
}
