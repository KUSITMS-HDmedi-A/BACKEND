package HDmedi.Server.domain.alram.repository;

import HDmedi.Server.domain.alram.entity.Alarm;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AlramRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndChildMedicine(Date today1, Date today2, ChildMedicine childMedicine);

    // startDate 보다 알람 시작 날짜가 이전이고, isDone 필드가 false 인 alarm 리스트를 쿼리
    List<Alarm> findAllByStartDateLessThanAndIsActivatedIsFalse(LocalDate startDate);
}
