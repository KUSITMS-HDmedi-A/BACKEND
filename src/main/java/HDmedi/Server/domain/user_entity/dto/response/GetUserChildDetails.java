package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.user_child.entity.UserChild;
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
    List<UserChildDetailInfo> childInfos;

    public static GetUserChildDetails of(List<UserChildDetailInfo> childInfos) {
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
        LocalDateTime createdDate;
        LocalDateTime modifiedDate;
        LocalDate startDate;
        LocalDate endDate;
        String label;
        String record;
        String medicineName;

        public static AlarmInfo of(Alarm alarm, MedicineItem medicineItem) {
            return new AlarmInfo(
                    alarm.getCreatedDate(),
                    alarm.getModifiedDate(),
                    alarm.getStartDate(),
                    alarm.getEndDate(),
                    alarm.getLabel(),
                    alarm.getRecord(),
                    medicineItem.getName());
        }
    }
}
