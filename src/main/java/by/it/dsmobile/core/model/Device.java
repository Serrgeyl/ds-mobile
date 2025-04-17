package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static by.it.dsmobile.config.AppConstants.SHORT_TYPE;

@Entity
@Table(name = "device")
@Getter
@Setter
public class Device extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Column(name = "address", columnDefinition = SHORT_TYPE)
    private Integer address;

    @Column(name = "left_is_entry")
    private Boolean leftIsEntry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id")
    private Controller controller;

    @Column(name = "note")
    private String note;

}
