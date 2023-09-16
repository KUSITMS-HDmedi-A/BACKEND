package HDmedi.Server.domain.alarm.dto.response;

import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatchDoseSignResponse {
    Long alarmId;
    public static PatchDoseSignResponse of (AlarmDate alarmDate){
        return new PatchDoseSignResponse(alarmDate.getId());
    }
}
