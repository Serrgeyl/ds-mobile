package by.it.dsmobile.core.model;

import by.it.dsmobile.api.dto.response.EventSummary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

import static by.it.dsmobile.config.AppConstants.SHORT_TYPE;

@NamedNativeQuery(
        name = "events_summary_query",
        query = "select date(e.fired_at) as date, " +
                "e.user_id as userId, " +
                "min(case when (d.left_is_entry is true and e.direction = 1) or (d.left_is_entry is false and e.direction = 2) then e.fired_at end) as inTime, " +
                "max(case when (d.left_is_entry is true and e.direction = 2) or (d.left_is_entry is false and e.direction = 1) then e.fired_at end) as outTime, " +
                "count(e.id) as count " +
                "from event e " +
                "left join device d on e.controller_id = d.controller_id and e.device_address = d.address " +
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
@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_type", columnDefinition = SHORT_TYPE)
    private Integer eventType;

    @Column(name = "direction", columnDefinition = SHORT_TYPE)
    private Integer direction;

    @Column(name = "fired_at")
    private OffsetDateTime firedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id")
    private Controller controller;

    @Column(name = "device_address", columnDefinition = SHORT_TYPE)
    private Integer deviceAddress;

    @Column(name = "created_at")
    public OffsetDateTime createdAt;

}
