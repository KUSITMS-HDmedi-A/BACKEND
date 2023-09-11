package HDmedi.Server.domain.user_child.controller;


import HDmedi.Server.domain.question.dto.response.AdhdTestResultResponseDto;
import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.HomeResponseDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.service.ChildService;
import HDmedi.Server.domain.user_entity.controller.AuthController;
import HDmedi.Server.domain.user_entity.dto.response.TokenResponseDto;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/home")
public class ChildController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChildController.class);

    private final ChildService childService;

    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public ChildController(ChildService childService, JwtTokenProvider jwtTokenProvider) {
        this.childService = childService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = "/")
    public HomeResponseDto home(@AuthenticationPrincipal CustomUser customUser)  {

        HomeResponseDto homeResponseDto = childService.homePage(customUser.getUserId());

        LOGGER.info("데이터 전달완료");

        return homeResponseDto;
    }

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = ResponseDto.class)
    @ApiOperation(value = "새 캐릭터 추가", notes = "유저의 캐릭터 추가")
    @PostMapping(value = "/new-child")
    public ResponseDto newChild(
            @AuthenticationPrincipal CustomUser customUser,
            @ApiParam(value = "요청 해줘", required = true) @Validated @RequestBody  NewChildRequestDto newChildRequestDto
    )  {
        LOGGER.info(String.valueOf(customUser.getUserId()));

       ResponseDto responseDto = childService.enrollChild(customUser.getUserId(), newChildRequestDto);


        LOGGER.info("등록 완료");

        return responseDto;
    }







}
