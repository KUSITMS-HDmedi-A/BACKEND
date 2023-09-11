package HDmedi.Server.domain.question.controller;

import HDmedi.Server.domain.question.dto.request.AdhdTestResultRequestDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResponseDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResultResponseDto;
import HDmedi.Server.domain.question.service.QuestionService;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adhd")
public class AdhdController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdhdController.class);
    private final JwtTokenProvider jwtTokenProvider;

    private final QuestionService questionService;

    @Autowired
    public AdhdController(JwtTokenProvider jwtTokenProvider, QuestionService questionService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.questionService = questionService;
    }

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = AdhdTestResultResponseDto.class)
    @ApiOperation(value = "Adhd 결과", notes = "Adhd 결과 출력")
    @PostMapping ("/test/result")
    public AdhdTestResultResponseDto adhdTestResult(@AuthenticationPrincipal CustomUser customUser,
               @ApiParam(value = "요청 해줘", required = true) @Validated @RequestBody AdhdTestResultRequestDto adhdTestResultRequestDto

    )  {

       AdhdTestResultResponseDto adhdTestResultResponseDto = questionService.testResult(
               customUser.getUserId(),
               adhdTestResultRequestDto.getCharacter(),
               adhdTestResultRequestDto.getScore()
       );

        LOGGER.info("설문 결과 전달완료");

        return adhdTestResultResponseDto;
    }



    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = AdhdTestResponseDto.class)
    @ApiOperation(value = "Adhd 테스트 데이터", notes = "Adhd 테스트 데이터(질문, 캐릭터들)")
    @GetMapping("/test")
    public AdhdTestResponseDto adhdTest(@AuthenticationPrincipal CustomUser customUser)  {

        AdhdTestResponseDto adhdTestResponse = questionService.testStart(customUser.getUserId());

        LOGGER.info("설문 데이터 전달완료");

        return adhdTestResponse;
    }






}
