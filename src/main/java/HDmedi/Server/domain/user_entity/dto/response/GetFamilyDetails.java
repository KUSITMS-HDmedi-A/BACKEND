package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetFamilyDetails {
    List<GetUserChildAlarms> familyInfo;
    Long userId;

    public static GetFamilyDetails of(List<GetUserChildAlarms> familyInfo, UserEntity user) {
        return new GetFamilyDetails(
                familyInfo,
                user.getId()
        );
    }
}
