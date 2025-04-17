package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pass_order")
@Getter
@Setter
public class PassOrder extends UpdatableEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "reason")
    private PassOrderReason reason;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private PassOrderStatus status;

    @Column(name = "pass_code")
    private String passCode;

    @Column(name = "note")
    private String notes;

    @Column(name = "mobile_request")
    private Boolean mobileRequest;

}
