package by.it.dsmobile.core.repository;

import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(name = "events_summary_query", nativeQuery = true)
    List<EventSummary> retrieveEventsSummary(
            @Param("ids") List<Integer> ids,
            @Param("startDate") LocalDate startDate,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(
            value = "select count(*) from(select date(fired_at) from event where user_id = :id and fired_at >= :startDate group by date(fired_at)) as subselect",
            nativeQuery = true
    )
    int getEventsSummaryCount(@Param("id") int id, @Param("startDate") LocalDate startDate);

    List<Event> findAllByUserIdAndFiredAtBetween(Integer userId, OffsetDateTime start, OffsetDateTime end);

}
