package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.user_child.entity.UserChild;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserChildDetails {
    UserChildDetailInfo childInfos;

    public static GetUserChildDetails of(UserChildDetailInfo childInfos) {
        return new GetUserChildDetails(childInfos);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class UserChildDetailInfo {
        Long id;
        String name;
        String isAdhd;
        String relation;
        Integer score;
        List<AlarmInfo> alarmInfos;

        public static UserChildDetailInfo of(UserChild userChild, List<AlarmInfo> alarmInfos) {
            return new UserChildDetailInfo(
                    userChild.getId(),
                    userChild.getName(),
                    userChild.getIsADHD(),
                    userChild.getRelation(),
                    userChild.getScore(),
                    alarmInfos
            );
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class AlarmInfo {
        Long alarmId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createdDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime modifiedDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate endDate;
        String label;
        String record;
        String purpose;
        Long childMedicineId;
        Boolean doseSign;

        public static AlarmInfo of(Alarm alarm, ChildMedicine childMedicine, AlarmDate alarmDate) {
            return new AlarmInfo(
                    alarm.getId(),
                    alarm.getCreatedDate(),
                    alarm.getModifiedDate(),
                    alarm.getStartDate(),
                    alarm.getEndDate(),
                    alarm.getLabel(),
                    alarm.getRecord(),
                    childMedicine.getPurpose(),
                    childMedicine.getId(),
                    alarmDate.getDoseSign()
            );
        }
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class MedicineDto {
        Long id;
        String purpose;
        String name;
        String effect;

        public static MedicineDto of(ChildMedicine childMedicine, MedicineItem item) {
            return new MedicineDto(
                    childMedicine.getId(),
                    childMedicine.getPurpose(),
                    item.getName(),
                    item.getEffect()
            );
        }
    }
}
