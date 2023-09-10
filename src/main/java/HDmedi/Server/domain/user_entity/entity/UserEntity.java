package HDmedi.Server.domain.user_entity.entity;

import HDmedi.Server.domain.user_entity.UserRole;
import HDmedi.Server.domain.user_entity.UserStatus;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AllArgsConstructor
@Table(name = "tb_user")
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_entity_id")
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "userEntity")
    private List<UserChild> userChildEntities = new ArrayList<>();
}
