package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static by.it.dsmobile.core.util.AppConstants.SHORT_TYPE;

@Entity
@Getter
@Setter
@Table(name = "service")
public class Service extends UpdatableEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "disposable", nullable = false)
    private Boolean disposable;

    @Column(name = "sms_notify", nullable = false)
    private Boolean smsNotify;

    @Column(name = "push_notify", nullable = false)
    private Boolean pushNotify;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToMany(mappedBy = "services")
    private List<User> users;

}
