package HDmedi.Server.fcm.dto;

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

    public static FCMNotificationResponseDto of(Long userId) {
        return new FCMNotificationResponseDto(userId);
    }
}
