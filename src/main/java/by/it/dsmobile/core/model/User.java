package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "security_code_expiration_date")
    private OffsetDateTime securityCodeExpirationDate;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "balance")
    private Double balance;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "access_admin")
    private Boolean accessAdmin;

    @Column(name = "mobile_access")
    private Boolean mobileAccess;

    @Column(name = "note")
    private String note;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany
    @JoinTable(name= "groups_users",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> groups;

    @ManyToMany
    @JoinTable(name = "service_to_user",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") })
    private List<Service> services;

}
