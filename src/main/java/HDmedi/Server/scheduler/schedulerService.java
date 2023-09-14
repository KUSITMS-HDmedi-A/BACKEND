package HDmedi.Server.scheduler;

import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
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
public class schedulerService {
    private final AlramRepository alramRepository;
    private final FirebaseService firebaseService;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 500) // 1초에 1번씩
    public void alarmScheduling() {
        LocalDate curDate = LocalDate.now();
        LocalTime curTime = LocalTime.now();
        log.info("[alarm scheduler is working at " + curDate + " " + curTime);
        List<Alarm> alarms = alramRepository.findAllByStartDateLessThanAndIsActivatedIsTrue(curDate);

        for (Alarm alarm : alarms) {
            if (alarm.getTime().equals(curTime) && alarm.getIsActivated()) {
                Long userId = alarm.getId();
                String title = "";
                String body = "";
                FCMNotificationRequestDto request = FCMNotificationRequestDto.builder()
                        .targetUserId(userId)
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
