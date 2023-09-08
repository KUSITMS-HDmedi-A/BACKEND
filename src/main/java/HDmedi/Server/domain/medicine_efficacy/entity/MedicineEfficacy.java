package HDmedi.Server.domain.medicine_efficacy.entity;

import HDmedi.Server.domain.efficacy_tag.entity.EfficacyTag;
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
@Table(name = "tb_medicine_item")
public class MedicineEfficacy extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_efficacy_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "efficacy_tag_id")
    private EfficacyTag efficacyTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_item_id")
    private MedicineItem medicineItem;
}

