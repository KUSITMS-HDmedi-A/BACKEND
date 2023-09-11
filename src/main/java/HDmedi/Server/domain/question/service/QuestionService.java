package HDmedi.Server.domain.question.service;

import HDmedi.Server.domain.question.dto.response.AdhdTestResponseDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResultResponseDto;

public interface QuestionService {

    AdhdTestResponseDto testStart(Long userId);

    AdhdTestResultResponseDto testResult(Long userId, String character, int[] score);


}
