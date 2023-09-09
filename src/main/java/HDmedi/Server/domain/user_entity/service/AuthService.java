package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.user_entity.dto.response.TokenResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.LogoutResponseDto;

public interface AuthService {

    LogoutResponseDto logout(String refreshToken);

    TokenResponseDto reissueToken(String refreshToken);

    TokenResponseDto kakaoLogin(String accessToken);
}
