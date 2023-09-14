package HDmedi.Server.domain.user_entity.service.Impl;

import HDmedi.Server.domain.user_entity.dto.response.LogoutResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.TokenResponseDto;
import HDmedi.Server.domain.user_entity.service.AuthService;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public LogoutResponseDto logout(String refreshToken) {
        return null;
    }

    @Override
    public TokenResponseDto reissueToken(String refreshToken) {
        return null;
    }

    @Override
    public TokenResponseDto kakaoLogin(String accessToken) {
        return null;
    }
}
