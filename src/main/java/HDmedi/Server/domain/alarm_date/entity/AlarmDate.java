package HDmedi.Server.domain.alarm_date.entity;

import HDmedi.Server.domain.alarm.entity.Alarm;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "tb_alram_date")
public class AlarmDate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alram_date_id")
    private Long id;

    @Column(name = "alram_date")
    private LocalDate alramDate;

    @Column(name = "dose_sign")
    private Boolean doseSign = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;


}
