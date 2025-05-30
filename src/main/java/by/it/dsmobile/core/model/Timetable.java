package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.WeekdayByIdConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static by.it.dsmobile.config.AppConstants.ENUM_ID_TYPE;

@Getter
@Setter
@Entity
@Table(name = "timetable")
public class Timetable extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "weekday_id", columnDefinition = ENUM_ID_TYPE)
    @Convert(converter = WeekdayByIdConverter.class)
    private Weekday weekday;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

}
