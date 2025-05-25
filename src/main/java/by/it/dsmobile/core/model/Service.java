package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static by.it.dsmobile.config.AppConstants.SHORT_TYPE;

@Entity
@Getter
@Setter
@Table(name = "service")
public class Service extends UpdatableEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "disposable")
    private Boolean disposable;

    @Column(name = "sms_notify")
    private Boolean smsNotify;

    @Column(name = "push_notify")
    private Boolean pushNotify;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(mappedBy = "services")
    private List<User> users;

}
