package HDmedi.Server.domain.user_entity.dto.response;

import HDmedi.Server.domain.user_child.entity.UserChild;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserDetails {
    Long id;
    List<UserChildInfo> childList;

    public static GetUserDetails of(Long id, List<UserChildInfo> childList) {
        return new GetUserDetails(id, childList);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class UserChildInfo {
        Long id;
        String name;
        String isAdhd;
        String relation;
        Integer score;

        public static UserChildInfo of(UserChild userChild) {
            return new UserChildInfo(
                    userChild.getId(),
                    userChild.getName(),
                    userChild.getIsADHD(),
                    userChild.getRelation(),
                    userChild.getScore());
        }
    }
}
