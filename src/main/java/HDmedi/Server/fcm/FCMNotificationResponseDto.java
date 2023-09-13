package HDmedi.Server.fcm;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FCMNotificationResponseDto {
    Long Id;

    public static FCMNotificationResponseDto of(UserEntity user) {
        return new FCMNotificationResponseDto(user.getId());
    }
}
