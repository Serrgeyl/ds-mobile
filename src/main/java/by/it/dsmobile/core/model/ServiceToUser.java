package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "service_to_user")
public class ServiceToUser extends UpdatableEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "active_from", columnDefinition = "DATE")
    private LocalDate activeFrom;

    @Column(name = "mobile_request")
    private Boolean mobileRequest = false;

}
