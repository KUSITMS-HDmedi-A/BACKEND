package HDmedi.Server.domain.user.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class ResponseDto {
    private int code;
    private String message;

    public static ResponseDto of (int code, String message){
        return new ResponseDto(code, message);
    }
}
