package by.it.dsmobile.core.model;

import by.it.dsmobile.api.dto.response.EventSummary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@NamedNativeQuery(
        name = "events_summary_query",
        query = "select date(e.fired_at) as date, " +
                "e.user_id as userId, " +
                "min(case when e.event_entry_type = 'IN' then e.fired_at end) as inTime, " +
                "max(case when e.event_entry_type = 'OUT' then e.fired_at end) as outTime, " +
                "count(e.id) as count " +
                "from event e " +
                "where e.user_id in :ids " +
                "group by date, userId " +
                "order by date desc",
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

    @Column(name = "event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "event_entry_type", nullable = false)
    @Enumerated(EnumType.STRING)
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
