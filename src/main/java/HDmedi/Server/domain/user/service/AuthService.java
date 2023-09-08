package HDmedi.Server.domain.user.service;

import HDmedi.Server.domain.user.dto.response.ReissueTokenResponseDto;
import HDmedi.Server.domain.user.dto.response.LogOutResponseDto;

public interface AuthService {

    LogOutResponseDto logout(Long userId);

    ReissueTokenResponseDto reissueToken(String refreshToken);
}
