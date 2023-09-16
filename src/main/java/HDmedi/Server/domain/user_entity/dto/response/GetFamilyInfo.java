package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetFamilyInfo {
    Long userId;
    List<ChildInfo> childInfos;

    public static GetFamilyInfo of(UserEntity user, List<ChildInfo> childInfos) {
        return new GetFamilyInfo(user.getId(), childInfos);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ChildInfo {
        Long id;
        String name;
        List<DoseInfo> doseInfos;

        public static ChildInfo of(UserChild userChild, List<DoseInfo> doseInfos) {
            return new ChildInfo(
                    userChild.getId(),
                    userChild.getName(),
                    doseInfos);
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class DoseInfo {
        String purpose;
        Integer period;

        public static DoseInfo of(ChildMedicine childMedicine) {
            LocalDate startDate = childMedicine.getStartDate();
            LocalDate now = LocalDate.now();
            Period period = Period.between(startDate, now);
            return new DoseInfo(
                    childMedicine.getPurpose(),
                    period.getDays()
            );
        }
    }
}
