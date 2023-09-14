package HDmedi.Server.fcm.service;

import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.domain.user_entity.repository.UserRepository;
import HDmedi.Server.fcm.dto.FCMNotificationRequestDto;
import HDmedi.Server.fcm.dto.FirebaseTokenRequest;
import HDmedi.Server.fcm.dto.FirebaseTokenResponse;
import HDmedi.Server.global.exception.notfound.NotFoundMemberException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseService {
    private static final String DEV_URL = "http://ec2-52-78-198-247.ap-northeast-2.compute.amazonaws.com";
    private static final String LOCAL_URL = "http://localhost:8080";

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void sendNotificationByToken(FCMNotificationRequestDto request) {
        UserEntity user = userRepository.findById(request.getTargetUserId()).orElseThrow(NotFoundMemberException::new);

        if (!Objects.isNull(user.getFirebaseToken())) {
            Notification notification = Notification.builder()
                    .setTitle(request.getTitle())
                    .setBody(request.getBody())
                    .build();
            Message message = Message.builder()
                    .setToken(user.getFirebaseToken())
                    .setNotification(notification)
                    .build();
            try {
                FirebaseMessaging.getInstance().send(message);
                log.info("\"알림을 성공적으로 전송했습니다. targetUserId = \" + request.getTargetUserId()");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.warn("알림 전송 실패");
        }
    }

    @Transactional
    public FirebaseTokenResponse patchToken(FirebaseTokenRequest request) {
        UserEntity user = userRepository.findById(request.getId()).orElseThrow(NotFoundMemberException::new);
        String token = request.getToken();
        user.setFirebaseToken(token);
        return FirebaseTokenResponse.of(user.getId());
    }
}
