package HDmedi.Server.domain.medicine_item.entity;

import HDmedi.Server.domain.medicines.entity.Medicines;
import HDmedi.Server.domain.tag.entity.Tag;
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
@Table(name = "tb_medicine_item")
public class MedicineItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_item_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture_url")
    private String pictureUrl; // 사진 url

    @OneToMany(mappedBy = "medicineItem")
    private List<Medicines> medicines = new ArrayList<>();

    @OneToMany(mappedBy = "medicineItem")
    private List<Tag> tags = new ArrayList<>();
}
