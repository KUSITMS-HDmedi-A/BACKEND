package HDmedi.Server.domain.userchild;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "tb_user_child")
public class UserChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Integer age;
    String relationShip;

    @Column(name = "is_adhd")
    String isADHD;
    Integer score;
}
