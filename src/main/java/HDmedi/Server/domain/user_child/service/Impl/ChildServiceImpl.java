package HDmedi.Server.domain.user_child.service.Impl;


import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.HomeResponseDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_child.service.ChildService;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildServiceImpl implements ChildService {

    UserChildRepository userChildRepository;

    @Autowired
    ChildServiceImpl(UserChildRepository userChildRepository){
        this.userChildRepository = userChildRepository;
    }

    @Override
    public HomeResponseDto homePage(Long userId) {


        UserEntity userEntity = new UserEntity();

        userEntity.setId(userId);


        Optional<UserChild> userChild = userChildRepository.findByUserEntity(userEntity);

        return null;
    }

    @Override
    public ResponseDto enrollChild(Long userId, NewChildRequestDto newChildRequestDto) {

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
