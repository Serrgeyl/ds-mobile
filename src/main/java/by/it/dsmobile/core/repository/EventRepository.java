package by.it.dsmobile.core.repository;

import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(name = "events_summary_query", nativeQuery = true)
    List<EventSummary> retrieveEventsSummary(@Param("ids") List<Integer> ids);

    List<Event> findAllByUserIdAndFiredAtBetween(Integer userId, OffsetDateTime start, OffsetDateTime end);

}
