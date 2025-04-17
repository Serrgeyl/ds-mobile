package by.it.dsmobile.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "push_token")
@NoArgsConstructor
@AllArgsConstructor
public class PushToken extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "token")
    private String token;

//    @Column(name = "platform", columnDefinition = "int2")
//    @Convert(converter = PlatformTypeByIdConverter.class)
    @Enumerated(EnumType.STRING)
    private PlatformType platform;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
