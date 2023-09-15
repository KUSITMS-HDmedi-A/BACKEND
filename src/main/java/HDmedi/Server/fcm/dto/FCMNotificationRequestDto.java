package HDmedi.Server.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetUserId; // 푸쉬 알람을 보낼 유저 아이디
    private String title; // 푸쉬 알람 제목
    private String body; // 푸쉬 알람 내용

    @Builder
    public FCMNotificationRequestDto(Long targetUserId, String title, String body) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
    }
}
