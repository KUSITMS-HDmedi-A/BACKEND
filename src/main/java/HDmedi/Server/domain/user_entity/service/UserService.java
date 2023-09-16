package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.medicine_item.repository.MedicineItemRepository;
import HDmedi.Server.domain.medicines.repository.MedicinesRepository;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.dto.response.GetUserChildDetails;
import HDmedi.Server.domain.user_entity.dto.response.GetUserDetails;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.global.exception.notfound.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserChildRepository userChildRepository;
    private final AlramRepository alramRepository;
    private final ChildMedicineRepository childMedicineRepository;
    private final MedicinesRepository medicinesRepository;
    private final MedicineItemRepository medicineItemRepository;

    public GetUserDetails getUserDetails(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        List<UserChild> childs = userChildRepository.findByUserEntity(user);
        List<GetUserDetails.UserChildInfo> childInfos = childs.stream()
                .map(GetUserDetails.UserChildInfo::of)
                .collect(Collectors.toList());
        return GetUserDetails.of(user.getId(), childInfos);
    }

    public GetUserChildDetails getUserChildDetails(Long childId) {
        UserChild userChild = userChildRepository.findById(childId)
                .orElseThrow(NotFoundMemberException::new);
        // 해당 자식 유저가 복용 중인 약
        List<ChildMedicine> medicinesForChild = childMedicineRepository.findByUserChild(userChild);
        Map<Alarm, ChildMedicine> alarmsForChild = new HashMap<>();
        for (ChildMedicine childMedicine : medicinesForChild) {
            Optional<Alarm> alarm = alramRepository.findByChildMedicine(childMedicine);
            if (alarm.isEmpty()) continue;
            // 해당 약에 대한 알림이 존재 하면
            alarmsForChild.put(alarm.get(), childMedicine);
        }

        List<GetUserChildDetails.AlarmInfo> alarmInfos = alarmsForChild.entrySet()
                .stream()
                .map(e ->
                        GetUserChildDetails.AlarmInfo.of(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        GetUserChildDetails.UserChildDetailInfo userChildDetailInfo
                = GetUserChildDetails.UserChildDetailInfo.of(userChild, alarmInfos);

        return GetUserChildDetails.of(userChildDetailInfo);
    }
}
