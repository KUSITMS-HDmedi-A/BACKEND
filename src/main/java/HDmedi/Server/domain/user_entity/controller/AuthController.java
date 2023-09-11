package HDmedi.Server.domain.user_entity.controller;


import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.TokenResponseDto;
import HDmedi.Server.domain.user_entity.dto.response.LogoutResponseDto;
import HDmedi.Server.domain.user_entity.service.AuthService;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/user/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider){
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @ApiImplicitParam(
            name = "refresh",
            value = "refreshToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-refresh-token"
    )
    @ApiResponse(code = 200, message = "Success", response = LogoutResponseDto.class)
    @ApiOperation(value = "로그아웃", notes = "redis의 유저 정보 삭제")
    @DeleteMapping("/logout")
    public LogoutResponseDto logout(HttpServletRequest request)  {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        LogoutResponseDto logoutResponseDto = authService.logout(refreshToken);

        LOGGER.info("로그아웃 완료");

        return logoutResponseDto;
    }


    @ApiImplicitParam(
            name = "refresh",
            value = "refreshToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-refresh-token"
    )
    @ApiResponse(code = 200, message = "Success", response = TokenResponseDto.class)
    @ApiOperation(value = "토큰 재발급", notes = "access, refresh 토큰 재발급")
    @GetMapping(value = "/reissue-token")
    public TokenResponseDto reissueToken(HttpServletRequest request)  {

        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        TokenResponseDto reissueTokenResponseDto = authService.reissueToken(refreshToken);

        LOGGER.info("토큰 재발급 완료");

        return reissueTokenResponseDto;
    }


    @ApiImplicitParam(
            name = "access",
            value = "kakaoAccessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-kakaoAccess-token"
    )
    @ApiResponse(code = 200, message = "Success", response = TokenResponseDto.class)
    @ApiOperation(value = "카카오 로그인", notes = "자동 회원가입 및 access, refresh 토큰 발급")
    @PostMapping(value = "/kakao-login")
    public TokenResponseDto kakaoLogin(HttpServletRequest request)  {

        String accessToken = jwtTokenProvider.resolveAccessToken(request);

        TokenResponseDto reissueTokenResponseDto = authService.kakaoLogin(accessToken);

        LOGGER.info("카카오 로그인 완료");

        return reissueTokenResponseDto;
    }







}
