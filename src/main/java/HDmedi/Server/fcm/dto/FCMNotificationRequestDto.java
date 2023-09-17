package HDmedi.Server.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private String title; // 푸쉬 알람 제목
    private String body; // 푸쉬 알람 내용

    @Builder
    public FCMNotificationRequestDto(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
