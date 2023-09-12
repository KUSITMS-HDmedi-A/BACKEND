package HDmedi.Server.domain.alram.service.Impl;

import HDmedi.Server.domain.alram.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alram.service.AlramService;
import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlramServiceImpl implements AlramService {

    private final UserChildRepository userChildRepository;


    @Autowired
    public AlramServiceImpl(UserChildRepository userChildRepository) {
        this.userChildRepository = userChildRepository;
    }


    @Override
    public ResponseDto enrollRecord(Long userId, EnrollRecordRequestDto enrollRecordRequestDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        List<UserChild> userChild = userChildRepository.findByUserEntity(userEntity);







        
        return null;
    }
}
