package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

import static by.it.dsmobile.config.AppConstants.SHORT_TYPE;

@Entity
@Table(name = "controller")
@Getter
public class Controller extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "port", columnDefinition = SHORT_TYPE)
    private Integer port;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "pass_code_size", columnDefinition = SHORT_TYPE)
    private Integer passCodeSize;

    @OneToMany(mappedBy = "controller")
    private List<Device> devices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "note")
    private String note;

}
