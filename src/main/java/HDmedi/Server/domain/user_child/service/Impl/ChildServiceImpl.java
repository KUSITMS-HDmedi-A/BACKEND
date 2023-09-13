package HDmedi.Server.domain.user_child.service.Impl;


import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.HomeResponseDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_child.service.ChildService;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.global.exception.CustomExceptionContext;
import HDmedi.Server.global.exception.HDmediException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChildServiceImpl implements ChildService {

    UserChildRepository userChildRepository;
    AlramRepository alaramRepository;

    @Autowired
    ChildServiceImpl(UserChildRepository userChildRepository, AlramRepository alaramRepository){
        this.userChildRepository = userChildRepository;
        this.alaramRepository = alaramRepository;
    }

    @Override
    public HomeResponseDto homePage(Long userId) {


        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        String[] character;
        Long firstId;

        List<UserChild> userChild = userChildRepository.findByUserEntity(userEntity);

        character = new String[userChild.size()];

        for(int i = 0; i < userChild.size(); i++){
            character[i] = userChild.get(i).getName();
            firstId = userChild.get(0).getId();
        }

        Date today = new Date();
     //   List<Alarm> matchingEntities = alaramRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndChildMedicine(today,today,userChild.get(0));













        return null;
    }

    @Override
    public ResponseDto enrollChild(Long userId, NewChildRequestDto newChildRequestDto) {


        Optional<UserChild> userChildSelect = userChildRepository.findByName(newChildRequestDto.getName());
        if(userChildSelect.isPresent()){

            throw new HDmediException(CustomExceptionContext.INVALID_ADD_MEMBER.getHttpStatus(),
                    CustomExceptionContext.INVALID_ADD_MEMBER.getMessage(),
                    CustomExceptionContext.INVALID_ADD_MEMBER.getCode()
                    );

        }

        UserChild userChild = new UserChild();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        userChild.setName(newChildRequestDto.getName());
        userChild.setAge(newChildRequestDto.getAge());
        userChild.setRelation(newChildRequestDto.getRelationship());
        userChild.setUserEntity(userEntity);

        userChildRepository.save(userChild);

        ResponseDto responseDto = new ResponseDto();

        responseDto.setCode(200);
        responseDto.setMessage("완료");

        return responseDto;
    }
}
