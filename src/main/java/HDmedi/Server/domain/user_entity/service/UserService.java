package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.dto.response.GetUserDetails;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.global.exception.notfound.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserChildRepository userChildRepository;

    public GetUserDetails getUserDetails(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        List<UserChild> childs = userChildRepository.findByUserEntity(user);
        List<GetUserDetails.UserChildInfo> childInfos = childs.stream()
                        .map(GetUserDetails.UserChildInfo::of)
                        .collect(Collectors.toList());
        return GetUserDetails.of(user.getId(), childInfos);
    }
}
