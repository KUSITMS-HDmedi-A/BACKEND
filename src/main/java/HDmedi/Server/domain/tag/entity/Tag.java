package HDmedi.Server.domain.tag.entity;

import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "type")
    private String type;

    @Column(name="descritpion")
    private String description; // 태그에 대한 짧은 설명

    public boolean isItEfficacy() {
        // 해당 태그가 효능 태그 인지 확인 
        return Objects.equals(type, "E");
    }
    public boolean isItWarning() {
        // 해당 태그가 위험/경고 태그인지 확인
        return Objects.equals(type, "W");
    }
    public void setEfficacy(){
        this.type = "E";
    }
    public void setWarning(){
        this.type = "W";
    }

}
