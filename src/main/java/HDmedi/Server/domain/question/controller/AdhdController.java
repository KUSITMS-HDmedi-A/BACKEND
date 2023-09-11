package HDmedi.Server.domain.question.controller;

import HDmedi.Server.domain.question.dto.request.AdhdTestResultRequestDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResponseDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResultResponseDto;
import HDmedi.Server.domain.question.service.QuestionService;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
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


    @PostMapping ("/test/result")
    public AdhdTestResultResponseDto adhdTestResult(@AuthenticationPrincipal CustomUser customUser,
                                        @Validated @RequestBody AdhdTestResultRequestDto adhdTestResultRequestDto

    )  {

       AdhdTestResultResponseDto adhdTestResultResponseDto = questionService.testResult(
               customUser.getUserId(),
               adhdTestResultRequestDto.getCharacter(),
               adhdTestResultRequestDto.getScore()
       );

        LOGGER.info("설문 결과 전달완료");

        return adhdTestResultResponseDto;
    }

    @GetMapping("/test")
    public AdhdTestResponseDto adhdTest()  {

        AdhdTestResponseDto adhdTestResponse = questionService.testStart();

        LOGGER.info("설문 데이터 전달완료");

        return adhdTestResponse;
    }






}
