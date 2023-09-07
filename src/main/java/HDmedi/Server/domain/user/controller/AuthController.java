package HDmedi.Server.domain.user.controller;


import HDmedi.Server.domain.user.dto.response.ReissueTokenResponseDto;
import HDmedi.Server.domain.user.dto.response.ResponseDto;
import HDmedi.Server.domain.user.service.AuthService;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
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

    @DeleteMapping("/logout")
    public ResponseDto logout(@AuthenticationPrincipal CustomUser customUser)  {

        ResponseDto logoutResponseDto = authService.logout(customUser.getUserId());

        LOGGER.info("로그아웃 완료");

        return logoutResponseDto;
    }

    @GetMapping(value = "/reissue-token")
    public ReissueTokenResponseDto reissueToken(HttpServletRequest request)  {

        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        ReissueTokenResponseDto reissueTokenResponseDto = authService.reissueToken(refreshToken);

        LOGGER.info("토큰 재발급 완료");

        return reissueTokenResponseDto;
    }



}
