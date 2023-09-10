package HDmedi.Server.domain.alram.repository;

import HDmedi.Server.domain.alram.entity.Alarm;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AlramRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndChildMedicine(Date today1, Date today2, ChildMedicine childMedicine);

}
