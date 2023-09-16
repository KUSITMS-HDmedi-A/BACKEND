package HDmedi.Server.domain.child_medicine.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
public class DoseRecordResponseDto extends ResponseDto {

    List<DoseCharacterDto> characterList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class DoseCharacterDto{
        String alarmCount;
        String name;
        List<DoseAlarmDto> doseAlarmList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class DoseAlarmDto{
        String time;
        int count;
        boolean doseSign;
        String label;
        String record;
    }
}
