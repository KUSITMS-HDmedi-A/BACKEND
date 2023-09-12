package HDmedi.Server.domain.question.service.Impl;

import HDmedi.Server.domain.question.dto.response.AdhdTestResponseDto;
import HDmedi.Server.domain.question.dto.response.AdhdTestResultResponseDto;
import HDmedi.Server.domain.question.entity.Question;
import HDmedi.Server.domain.question.repository.QuestionRepository;
import HDmedi.Server.domain.question.service.QuestionService;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private  final UserChildRepository userChildRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);
    public QuestionServiceImpl(QuestionRepository questionRepository, UserChildRepository userChildRepository) {
        this.questionRepository = questionRepository;
        this.userChildRepository = userChildRepository;
    }

    @Override
    public AdhdTestResponseDto testStart(Long userId) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        List<Question> question = questionRepository.findAllByDescriptionIsNotNull();
        List<UserChild> userChildList = userChildRepository.findByUserEntity(userEntity);


      //  String[] questions = new String[question.size()];
        String[] characterList = new String[userChildList.size()];
        List<AdhdTestResponseDto.Questions> questionsList = new ArrayList<>(question.size());
        for(int i = 0; i < question.size(); i++){
           // questions[i] = question.get(i).getDescription();
            AdhdTestResponseDto.Questions questions = new AdhdTestResponseDto.Questions();
            questions.setDescription(question.get(i).getDescription());
            questions.setBlue(question.get(i).getSummary());

            questionsList.add(questions);


        }
        for(int i = 0; i < userChildList.size(); i++){
            characterList[i] = userChildList.get(i).getName();
        }

        AdhdTestResponseDto adhdTestResponseDto = new AdhdTestResponseDto();

        adhdTestResponseDto.setCode(200);
        adhdTestResponseDto.setMessage("OK");
        adhdTestResponseDto.setQuestionsList(questionsList);
        adhdTestResponseDto.setCharacter(characterList);

        return adhdTestResponseDto;
    }

    @Override
    public AdhdTestResultResponseDto testResult(Long userId, String character, int[] score) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        UserChild userChild = userChildRepository.findByNameAndUserEntity(character, userEntity);

        int scoreResult = sumArray(score);
        String assessResult = assessScore(scoreResult);

        userChild.setIsADHD(assessResult);
        userChild.setScore(scoreResult);

        userChildRepository.save(userChild);

        AdhdTestResultResponseDto adhdTestResultResponseDto = new AdhdTestResultResponseDto();
        adhdTestResultResponseDto.setCode(200);
        adhdTestResultResponseDto.setMessage("OK");
        adhdTestResultResponseDto.setResult(assessResult);
        adhdTestResultResponseDto.setScore(scoreResult);

        return adhdTestResultResponseDto;
    }

    public int sumArray(int[] score) {
        int sum = 0;

        for (int i = 0; i < score.length; i++) {
            sum += score[i];
        }

        return sum;
    }

    public String assessScore(int scoreResult) {
        if (scoreResult >= 0 && scoreResult <= 18) {
            return "크게 문제될 것이 없습니다";
        } else if (scoreResult >= 19 && scoreResult <= 54) {
            return "주의력결핍 및 과잉행동 장애일 가능성이 있습니다.";
        } else {
            return "점수 범위를 벗어난 값입니다.";
        }
    }
}
