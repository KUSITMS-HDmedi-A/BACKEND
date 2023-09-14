package HDmedi.Server.domain.alarm.repository;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AlramRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndChildMedicine(Date today1, Date today2, ChildMedicine childMedicine);


    List<Alarm> findAllByChildMedicine(ChildMedicine childMedicine);

    @Query("SELECT a FROM Alarm a JOIN FETCH a.childMedicine cm JOIN cm.userChild uc JOIN uc.userEntity ue WHERE ue = :userEntity")
    List<Alarm> findAlarmsByUserEntity(@Param("userEntity") UserEntity userEntity);


}
