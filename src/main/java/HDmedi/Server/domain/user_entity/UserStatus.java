package HDmedi.Server.domain.user_entity;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE, // 활성화
    INACTIVE, // 비활성화
    TERMINATE; // 탈퇴
    int value;
}
