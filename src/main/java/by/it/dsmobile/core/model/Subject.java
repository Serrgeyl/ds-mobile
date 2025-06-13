package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static by.it.dsmobile.core.util.AppConstants.SHORT_TYPE;

@Entity
@Table(name = "subject")
@Getter
@Setter
public class Subject extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Column(name = "name")
    private String name;

}
