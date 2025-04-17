package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_link")
@Getter
@Setter
public class UserLink extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "receive_sms")
    private boolean receiveSMS;

    @Column(name = "receive_push")
    private boolean receivePush;

}
