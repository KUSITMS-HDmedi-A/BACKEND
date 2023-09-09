package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.user_entity.dto.response.ReissueTokenResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.LogOutResponseDto;

public interface AuthService {

    LogOutResponseDto logout(Long userId);

    ReissueTokenResponseDto reissueToken(String refreshToken);
}
