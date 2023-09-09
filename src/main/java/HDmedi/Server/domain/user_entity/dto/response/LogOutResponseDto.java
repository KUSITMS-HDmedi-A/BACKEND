package HDmedi.Server.domain.user_entity.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class LogOutResponseDto {
    private int code;
    private String message;

    public static LogOutResponseDto of (int code, String message){
        return new LogOutResponseDto(code, message);
    }
}
