package HDmedi.Server.domain.user.service;

import HDmedi.Server.domain.user.dto.response.ReissueTokenResponseDto;
import HDmedi.Server.domain.user.dto.response.ResponseDto;

public interface AuthService {

    ResponseDto logout(Long userId);

    ReissueTokenResponseDto reissueToken(String refreshToken);
}
