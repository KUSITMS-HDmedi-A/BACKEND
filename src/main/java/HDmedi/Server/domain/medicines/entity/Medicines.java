package HDmedi.Server.domain.medicines.entity;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "tb_medicines")
public class Medicines extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicines_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_medicine_id")
    private ChildMedicine childMedicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_item_id")
    private MedicineItem medicineItem;
}
