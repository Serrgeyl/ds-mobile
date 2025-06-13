package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static by.it.dsmobile.core.util.AppConstants.SHORT_TYPE;

@Getter
@Setter
@Entity
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
public class City extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}