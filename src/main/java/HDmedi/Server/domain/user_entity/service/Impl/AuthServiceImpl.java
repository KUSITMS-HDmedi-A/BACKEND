package HDmedi.Server.domain.user_entity.service.Impl;

import HDmedi.Server.domain.user_entity.UserRole;
import HDmedi.Server.domain.user_entity.dto.response.KakaoUserResponse;
import HDmedi.Server.domain.user_entity.dto.response.TokenResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.LogoutResponseDto;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.domain.user_entity.service.AuthService;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import HDmedi.Server.global.exception.unauthorized.TokenExpiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    public JwtTokenProvider jwtTokenProvider;
    public RedisTemplate<String, String> redisTemplate;

    public UserRepository userRepository;

    private final WebClient webClient;


    @Autowired
    public AuthServiceImpl(WebClient webClient, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, RedisTemplate<String, String> redisTemplate) {
        this.webClient = webClient;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public LogoutResponseDto logout(String refreshToken) {
        redisTemplate.opsForHash().delete(refreshToken, "userId", "role");
        return LogoutResponseDto.of(HttpStatus.OK.value(), "완료");
    }

    @Override
    public TokenResponseDto reissueToken(String refreshToken) {

        if(!jwtTokenProvider.validateRefreshToken(refreshToken)){
            throw new TokenExpiredException();
        }

        String userId = (String) redisTemplate.opsForHash().get(refreshToken, "userId");
        String role = (String) redisTemplate.opsForHash().get(refreshToken, "role");

        TokenResponseDto reissueTokenResponse = TokenResponseDto
                .builder()
                .code(200)
                .message("OK")
                .accessToken(jwtTokenProvider.createAccessToken(Long.valueOf(userId),role))
                .refreshToken(refreshToken)
                .build();

        return reissueTokenResponse;

    }

    @Override
    public TokenResponseDto kakaoLogin(String accessToken) {

        UserEntity user;


        Mono<KakaoUserResponse> userInfoMono = getUserInfo(accessToken);
        KakaoUserResponse userInfo = userInfoMono.block();

        Optional<UserEntity> userData = userRepository.findByEmail(String.valueOf(userInfo.getId()));
        LOGGER.info(String.valueOf(userData));
        if(userData.isEmpty()){
            user = UserEntity.builder()
                    .email(String.valueOf(userInfo.getId()))
                    .userRole(UserRole.valueOf("USER"))
                    .build();

            userRepository.save(user);
        }

        Optional<UserEntity> userLoginData = userRepository.findByEmail(String.valueOf(userInfo.getId()));

        String refreshToken = jwtTokenProvider.createRereshToken();

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .message("성공")
                .code(200)
                .accessToken(jwtTokenProvider.createAccessToken(
                        userLoginData.get().getId(),
                        String.valueOf(userLoginData.get().getUserRole())))
                .refreshToken(refreshToken)
                .build();

        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"userId", String.valueOf(userLoginData.get().getId()));
        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"role", String.valueOf(userLoginData.get().getUserRole()));


        return tokenResponseDto;
    }


    public void deleteValueByKey(String key) {
        redisTemplate.delete(key);
    }

    public Mono<KakaoUserResponse> getUserInfo(String accessToken) {
        return webClient
                .get()
                .uri("https://kapi.kakao.com/v2/user/me") // 카카오 사용자 정보 엔드포인트
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserResponse.class);
    }


}
