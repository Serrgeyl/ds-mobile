package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
public class Group extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "group_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "mapping")
    private String mapping;

    @ManyToOne
    @JoinColumn(name = "class_teacher_id")
    private User classTeacher;

    @Column(name = "note")
    private String note;

    @ManyToMany
    @JoinTable(name= "groups_users",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> users;

}