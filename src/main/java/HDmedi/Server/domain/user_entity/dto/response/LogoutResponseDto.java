package HDmedi.Server.domain.user_entity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogoutResponseDto {


    private int code;


    private String message;

    public static LogoutResponseDto of (int code, String message){
        return new LogoutResponseDto(code, message);
    }
}
