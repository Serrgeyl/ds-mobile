package by.it.dsmobile.core.model;

import by.it.dsmobile.core.repository.converter.PassOrderStatusTypeByIdConverter;
import by.it.dsmobile.core.repository.converter.PassOrderTypeByIdConverter;
import by.it.dsmobile.core.repository.converter.PassTypeByIdConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static by.it.dsmobile.core.util.AppConstants.ENUM_ID_TYPE;

@Getter
@Setter
@Entity
@Table(name = "pass_order")
public class PassOrder extends UpdatableEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = PassOrderTypeByIdConverter.class)
    @Column(name = "pass_order_type_id", columnDefinition = ENUM_ID_TYPE)
    private PassOrderType passOrderType;

    @Convert(converter = PassOrderStatusTypeByIdConverter.class)
    @Column(name = "pass_order_status_type_id", columnDefinition = ENUM_ID_TYPE)
    private PassOrderStatusType passOrderStatusType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "pass_type_id", columnDefinition = ENUM_ID_TYPE)
    @Convert(converter = PassTypeByIdConverter.class)
    private PassType passType;

    @Column(name = "pass_code")
    private String passCode;

    @Column(name = "mobile_request")
    private Boolean mobileRequest;

    @Column(name = "note")
    private String note;

}
