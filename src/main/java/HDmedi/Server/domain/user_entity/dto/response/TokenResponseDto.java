package HDmedi.Server.domain.user_entity.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class TokenResponseDto {
    private int code;
    private String message;
    private String accessToken;
    private String refreshToken;
}