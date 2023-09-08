package HDmedi.Server.domain.user.entity;

import HDmedi.Server.domain.user.UserRole;
import HDmedi.Server.domain.user.UserStatus;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "tb_user")
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    String password;
    String email;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;
}
