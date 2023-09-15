package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.medicine_item.repository.MedicineItemRepository;
import HDmedi.Server.domain.medicines.entity.Medicines;
import HDmedi.Server.domain.medicines.repository.MedicinesRepository;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.dto.response.GetUserChildDetails;
import HDmedi.Server.domain.user_entity.dto.response.GetUserDetails;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.global.exception.notfound.NotFoundMemberException;
import com.google.firebase.auth.hash.Md5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
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
}
