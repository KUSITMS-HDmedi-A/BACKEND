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
    String title;
    String body;
    Long Id;

    public static FCMNotificationResponseDto of(String title, String body, Long userId) {
        return new FCMNotificationResponseDto(title, body, userId);
    }
}
