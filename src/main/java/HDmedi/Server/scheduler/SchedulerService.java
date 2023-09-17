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
import java.util.stream.Collectors;

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
            List<AlarmDate> alarmDates = alarmDateRepository.findByAlarm(alarm);
            List<LocalDate> dates = alarmDates.stream()
                    .map(AlarmDate::getDate).collect(Collectors.toList());

            List<AlarmDate> alarmInfos = alarmDateRepository.findAlarmDateByAlarm(alarm);
            LocalTime alarmAtTime = alarm.getTime(); // 알람이 울려야 할 시각
            Boolean isActivated = alarm.getIsActivated();
            // 해당 알림이 울려야 하는 날짜들 가져오기
            for (AlarmDate alarmDate : alarmInfos) {
                LocalDate alarmAtDate = alarmDate.getDate(); // 알람이 울려야 할 날짜
                Boolean doseSign = alarmDate.getDoseSign(); // 해당 날짜의 알람의 복용
                if (!alarmAtDate.equals(curDate)) {
                    log.info("[ 알람 날짜와 현재 날짜가 일치하지 않으므로 알람을 울리지 않습니다.  ]");
                    continue;
                }
                if (!alarmAtTime.equals(curTime)) {
                    log.info("[ 알람이 울리는 시간과 현재 시간이 일치하지 않으므로 알람을 울리지 않습니다. ]");
                    continue;
                }
                if (!isActivated) {
                    log.info("[ 알림이 비활성화 상태이므로 알림을 울리지 않습니다. ]");
                    continue;
                }
                if (doseSign) {
                    log.info("[해당 알림 날짜에 대한 doseSing 이 true 이므로 알림을 울리지 않습니다. ]");
                    continue;
                }
                    /*
                        1. 알림이 활성화 상태이고
                        2. curDate 가 date 와 같으며
                        3. 복약 체크가 false 이면
                        4. curTime 이 알람 시간과 같은 경우 에만 푸시 알림 전송
                    */
                Long childMedicineId = alarm.getChildMedicine().getId();
                UserChild child = childMedicineRepository.findById(childMedicineId).get().getUserChild();
                UserEntity user = child.getUserEntity();

                String title = "[HDmedi push alarm]";
                String body = child.getName() + " 님을 위한 복약 알림입니다.";
                FCMNotificationRequestDto request = FCMNotificationRequestDto.builder()
                        .title(title)
                        .body(body).build();
                firebaseService.sendNotificationByToken(request, user.getId());
            }
            if (alarm.getEndDate().isBefore(curDate)) {
                log.info("알람의 종료 날짜가 현재 날짜보다 이전일이므로 비활성화 처리합니다.");
                alarm.inactivateAlarm(); // 알람 비 활성화 처리
            }
        }
    }
}
