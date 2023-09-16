package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_child.entity.UserChild;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserChildAlarms {
    Long childId;
    String name;
    List<AlarmInfo> alarmInfos;

    public static GetUserChildAlarms of(UserChild userChild, List<AlarmInfo> alarmInfos) {
        return new GetUserChildAlarms(
                userChild.getId(),
                userChild.getName(),
                alarmInfos
        );
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class AlarmInfo {
        String purpose;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
        LocalTime alarmAt;
        boolean doseSign;
        Long alarmId;
        Long alarmSequence;

        public static AlarmInfo of(ChildMedicine medicine, AlarmDate alarmDate, Alarm alarm) {
            return new AlarmInfo(
                    medicine.getPurpose(),
                    alarm.getTime(),
                    alarmDate.getDoseSign(),
                    alarm.getId(),
                    alarmDate.getId()
            );
        }
    }
}
