package HDmedi.Server.domain.user.service.Impl;

import HDmedi.Server.domain.user.dto.response.ReissueTokenResponseDto;
import HDmedi.Server.domain.user.dto.response.ResponseDto;
import HDmedi.Server.domain.user.service.AuthService;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import HDmedi.Server.global.exception.unauthorized.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class AuthSeriveImpl implements AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthSeriveImpl.class);

    public JwtTokenProvider jwtTokenProvider;
    public RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseDto logout(Long userId) {
        deleteValueByKey(String.valueOf(userId));

        ResponseDto logoutResponse = new ResponseDto();
        logoutResponse.setCode(200);
        logoutResponse.setMessage("완료");


        return logoutResponse;
    }

    @Override
    public ReissueTokenResponseDto reissueToken(String refreshToken) {

        if(!jwtTokenProvider.validateRefreshToken(refreshToken)){
            throw new TokenExpiredException();
        }

        String userId = (String) redisTemplate.opsForHash().get(refreshToken, "userId");
        String role = (String) redisTemplate.opsForHash().get(refreshToken, "role");

        ReissueTokenResponseDto reissueTokenResponse = ReissueTokenResponseDto
                .builder()
                .code(200)
                .message("OK")
                .accessToken("Bearer " +jwtTokenProvider.createAccessToken(Long.valueOf(userId),role))
                .refreshToken("Bearer " +refreshToken)
                .build();

        return reissueTokenResponse;

    }

    public void deleteValueByKey(String key) {
        redisTemplate.delete(key);
    }
}
