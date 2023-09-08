package HDmedi.Server.domain.medicine_warning.entity;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.warnning_tag.entity.WarningTag;
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
@Table(name = "tb_medicine_warning_item")
public class MedicineWarning extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_warning_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warning_tag_id")
    private WarningTag warningTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_item_id")
    private MedicineItem medicineItem;
}
