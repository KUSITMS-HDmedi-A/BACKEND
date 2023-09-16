package HDmedi.Server.domain.alarm_date.repository;


import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlarmDateRepository extends JpaRepository<AlarmDate, Long> {


    @Query("SELECT ad FROM AlarmDate ad " +
            "JOIN FETCH ad.alarm a " +
            "JOIN FETCH a.childMedicine cm " +
            "JOIN FETCH cm.userChild uc " +
            "JOIN uc.userEntity ue " +
            "WHERE ue = :userEntity " +
            "AND ad.date = :todayDate")
    List<AlarmDate> findTodayAlarmDatesByUserEntity(@Param("userEntity") UserEntity userEntity, @Param("todayDate") LocalDate todayDate);

    List<LocalDate> findDatesByAlarm(Alarm alarm);

    List<AlarmDate> findAlarmDateByAlarm(Alarm alarm);

}
