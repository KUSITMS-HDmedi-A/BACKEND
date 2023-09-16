package HDmedi.Server.domain.user_entity.service;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.alarm_date.repository.AlarmDateRepository;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.medicine_item.repository.MedicineItemRepository;
import HDmedi.Server.domain.medicines.repository.MedicinesRepository;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.dto.response.*;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.global.exception.notfound.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserChildRepository userChildRepository;
    private final AlramRepository alramRepository;
    private final AlarmDateRepository alarmDateRepository;
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
        List<GetUserChildDetails.AlarmInfo> alarmInfos = new ArrayList<>();

        for (ChildMedicine childMedicine : medicinesForChild) {
            List<Alarm> alarms = alramRepository.findByChildMedicine(childMedicine);
            for (Alarm alarm : alarms) {
                // 해당 약에 대한 알림이 존재 하면
                alarmInfos.add(GetUserChildDetails.AlarmInfo.of(alarm, childMedicine));
            }
        }

        GetUserChildDetails.UserChildDetailInfo userChildDetailInfo
                = GetUserChildDetails.UserChildDetailInfo.of(userChild, alarmInfos);

        return GetUserChildDetails.of(userChildDetailInfo);
    }

    public GetFamilyDetails getUserChildAlarms(Long userId) {
//        Long childId;
//        String name;
//        List<GetUserChildAlarms.AlarmInfo> alarmInfos;
//        infos: ChildMedicine medicine, AlarmDate alarmDate, Alarm alarm
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        List<UserChild> userChildList = user.getUserChildEntities();
        List<GetUserChildAlarms> familyDetails = new ArrayList<>();
        for (UserChild userChild : userChildList) {
            Set<ChildMedicine> medicinesForChild = new HashSet<>(userChild.getChildMedicines());
            List<GetUserChildAlarms.AlarmInfo> alarmInfos = new ArrayList<>();
            Set<Long> alarmSet = new HashSet<>();
            for (ChildMedicine childMedicine : medicinesForChild) {
                List<Alarm> alarms = alramRepository.findByChildMedicine(childMedicine);
                for (Alarm alarm : alarms) {
                    List<AlarmDate> alarmDates = alarmDateRepository.findAlarmDateByAlarm(alarm);
                    // 해당 알람에 대한 세부 정보 리스트
                    for (AlarmDate date : alarmDates) {
                        if (alarmSet.contains(alarm.getId())) continue;
                        alarmSet.add(alarm.getId());
                        alarmInfos.add(GetUserChildAlarms.AlarmInfo.of(childMedicine, date, alarm));
                    }
                }
            }
            GetUserChildAlarms childAlarmsDetail = GetUserChildAlarms.of(userChild, alarmInfos);
            familyDetails.add(childAlarmsDetail);
        }
        return GetFamilyDetails.of(familyDetails, user);
    }

    public GetFamilyInfo getFamilyInfo(Long userId) {
        UserEntity user = userRepository.findById(userId) // 유저 정보 가져와서
                .orElseThrow(NotFoundMemberException::new);
        List<UserChild> userChildList = user.getUserChildEntities(); // 해당 유저의 구성원들 정보를 가져오고
        List<GetFamilyInfo.ChildInfo> childInfos = new ArrayList<>();
        for (UserChild userChild : userChildList) {
            // 구성원들이 복용하고 있는 약 정보를 가져와서
            List<ChildMedicine> childMedicines = childMedicineRepository.findByUserChild(userChild);
            List<GetFamilyInfo.DoseInfo> doseInfos = new ArrayList<>();
            for (ChildMedicine childMedicine : childMedicines) {
                doseInfos.add(GetFamilyInfo.DoseInfo.of(childMedicine));
            }
            childInfos.add(
                    GetFamilyInfo.ChildInfo.of(userChild, doseInfos)
            );
        }
        return GetFamilyInfo.of(user, childInfos);
    }
}
