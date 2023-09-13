package HDmedi.Server.domain.tag.entity;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.tag.TagType;
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
@Table(name = "tb_tag")
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_item_id")
    MedicineItem medicineItem;

//    @Enumerated(EnumType.STRING)
//    private TagType type;
    @Column(name = "type")
    private String type;

}
