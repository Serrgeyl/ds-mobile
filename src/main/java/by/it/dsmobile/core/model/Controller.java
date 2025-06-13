package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import static by.it.dsmobile.core.util.AppConstants.JSONB_TYPE;
import static by.it.dsmobile.core.util.AppConstants.SHORT_TYPE;

@Getter
@Entity
@Table(name = "controller")
public class Controller extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = SHORT_TYPE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "controller_type", nullable = false)
    private ControllerType controllerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", columnDefinition = JSONB_TYPE)
    private ControllerAttributes attributes;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
