package HDmedi.Server.domain.alarm.repository;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AlramRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndChildMedicine(Date today1, Date today2, ChildMedicine childMedicine);


    List<Alarm> findAllByChildMedicine(ChildMedicine childMedicine);
}
