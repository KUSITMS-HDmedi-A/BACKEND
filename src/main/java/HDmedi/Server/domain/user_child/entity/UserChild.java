package HDmedi.Server.domain.user_child.entity;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AllArgsConstructor
@Table(name = "tb_user_child")
public class UserChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_child_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
    @Column(name = "relation")
    private String relation;
    @Column(name = "is_adhd")
    private String isADHD;
    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    //@OneToMany(mappedBy = "userChild")
    //private List<ChildMedicine> childMedicines = new ArrayList<>();
}
