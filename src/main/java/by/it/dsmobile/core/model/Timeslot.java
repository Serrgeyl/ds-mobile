package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

import static by.it.dsmobile.core.util.AppConstants.SHORT_TYPE;

@Entity
@Table(name = "timeslot")
@Getter
@Setter
public class Timeslot extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Column(name = "lesson_number", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer lessonNumber;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "name")
    private String name;

}
