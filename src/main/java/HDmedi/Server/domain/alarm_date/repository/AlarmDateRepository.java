package HDmedi.Server.domain.alarm_date.repository;


import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlarmDateRepository extends JpaRepository<AlarmDate, Long> {

    AlarmDate findByAlramDate(LocalDate localDate);
}
