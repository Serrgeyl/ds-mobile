package by.it.dsmobile.core.model;

import by.it.dsmobile.api.dto.response.EventSummary;
import by.it.dsmobile.core.repository.converter.EventEntryTypeByIdConverter;
import by.it.dsmobile.core.repository.converter.EventTypeByIdConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

import static by.it.dsmobile.core.util.AppConstants.ENUM_ID_TYPE;

@NamedNativeQuery(
        name = "events_summary_query",
        query = "select date(e.fired_at) as date, " +
                "e.user_id as userId, " +
                "min(case when e.event_entry_type_id = 1 then e.fired_at end) as inTime, " +
                "max(case when e.event_entry_type_id = 2 then e.fired_at end) as outTime, " +
                "count(e.id) as count " +
                "from event e " +
                "where e.user_id in :ids and " +
                "e.fired_at >= :startDate " +
                "group by date, userId " +
                "order by date desc " +
                "limit :limit offset :offset",
        resultSetMapping = "event_summary_dto"
)
@SqlResultSetMapping(
        name = "event_summary_dto",
        classes = @ConstructorResult(
                targetClass = EventSummary.class,
                columns = {
                        @ColumnResult(name = "date", type = Date.class),
                        @ColumnResult(name = "userId", type = Integer.class),
                        @ColumnResult(name = "inTime", type = Date.class),
                        @ColumnResult(name = "outTime", type = Date.class),
                        @ColumnResult(name = "count", type = Integer.class)
                }
        )
)
@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_type_id", columnDefinition = ENUM_ID_TYPE)
    @Convert(converter = EventTypeByIdConverter.class)
    private EventType eventType;

    @Column(name = "event_entry_type_id", columnDefinition = ENUM_ID_TYPE)
    @Convert(converter = EventEntryTypeByIdConverter.class)
    private EventEntryType eventEntryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id")
    private Controller controller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "fired_at")
    private OffsetDateTime firedAt;

    @Column(name = "created_at")
    public OffsetDateTime createdAt;

}
