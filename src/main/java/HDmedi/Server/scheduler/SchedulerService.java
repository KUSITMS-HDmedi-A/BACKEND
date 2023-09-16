package HDmedi.Server.scheduler;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.alarm_date.repository.AlarmDateRepository;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.fcm.dto.FCMNotificationRequestDto;
import HDmedi.Server.fcm.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class SchedulerService {
    private final AlramRepository alramRepository;
    private final AlarmDateRepository alarmDateRepository;
    private final FirebaseService firebaseService;
    private final UserRepository userRepository;
    private final ChildMedicineRepository childMedicineRepository;

    @Scheduled(fixedRate = 60000) // 1초에 1번씩
    public void alarmScheduling() {
        LocalDate curDate = LocalDate.now();
        LocalTime curTime = LocalTime.now();
        log.info("[alarm scheduler is working at " + curDate + " " + curTime);
        List<Alarm> alarms = alramRepository.findAllByStartDateLessThanAndIsActivatedIsTrue(curDate);

        for (Alarm alarm : alarms) {
            List<LocalDate> dates = alarmDateRepository.findDatesByAlarm(alarm);
            AlarmDate alarmInfo = alarmDateRepository.findAlarmDateByAlarm(alarm);
            Boolean doseSign = alarmInfo.getDoseSign();
            // 해당 알림이 울려야 하는 날짜들 가져오기
            for (LocalDate date : dates) {
                if (alarm.getTime().equals(curTime) && date.isEqual(curDate) && alarm.getIsActivated() && !doseSign) {
                    // 알림이 활성화 상태이고, curDate 가 date 와 같으며, 복약 체크가 false 이면, curTime 이 알람 시간과 같은 경우에만 푸시 알림 전송
                    Long childMedicineId = alarm.getChildMedicine().getId();
                    UserChild child = childMedicineRepository.findById(childMedicineId).get().getUserChild();
                    UserEntity user = child.getUserEntity();

                    String title = "[HDmedi push alarm]";
                    String body = child.getName() + " 님을 위한 복약 알림입니다.";
                    FCMNotificationRequestDto request = FCMNotificationRequestDto.builder()
                            .targetUserId(user.getId())
                            .title(title)
                            .body(body).build();
                    firebaseService.sendNotificationByToken(request);
                }
                if (alarm.getEndDate().isBefore(curDate)) {
                    alarm.inactivateAlarm(); // 알람 비활성화 처리
                }
            }
        }
    }
}
