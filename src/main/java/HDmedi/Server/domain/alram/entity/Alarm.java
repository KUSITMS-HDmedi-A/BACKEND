package HDmedi.Server.domain.alram.entity;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.global.config.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "tb_alarm")
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_medicine_id")
    ChildMedicine childMedicine; // 하나의 약은 여러 개의 알림을 가질 수 있다.

    @Column(name = "start_date")
    LocalDate startDate; // 알림 시작 날짜

    @Column(name = "end_date")
    LocalDate endDate; // 알림 끝 날짜

    @Column(name = "is_done")
    Boolean isDone = false; // 알림 상태 ( 기획 보고 enum 으로 바꾸는 것이 좋음 )

    @Column(name = "record")
    String record; // 알람/복용 기록

    @Column(name = "label")
    String label; // 메모
}
