package HDmedi.Server.domain.warnning_tag.entity;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
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
@Table(name = "tb_warning_tag")
public class WarningTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warning_tag_id")
    private Long id;

    @OneToMany(mappedBy = "warningTag")
    private List<MedicineItem> medicineWarnings = new ArrayList<>();
}
