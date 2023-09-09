package HDmedi.Server.domain.child_medicine.entity;

import HDmedi.Server.domain.alram.entity.Alarm;
import HDmedi.Server.domain.medicines.entity.Medicines;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "tb_child_medicine")
public class ChildMedicine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_medicine_id")
    private Long id;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "prescription_url")
    private String prescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_child_id")
    private UserChild userChild;

    @OneToMany(mappedBy = "childMedicine")
    List<Medicines> medicines = new ArrayList<>();

    @OneToMany(mappedBy = "childMedicine")
    List<Alarm> alarms = new ArrayList<>();
}
