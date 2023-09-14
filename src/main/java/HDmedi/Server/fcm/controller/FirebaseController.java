package HDmedi.Server.fcm.controller;

import HDmedi.Server.fcm.dto.FCMNotificationRequestDto;
import HDmedi.Server.fcm.dto.FCMNotificationResponseDto;
import HDmedi.Server.fcm.dto.FirebaseTokenRequest;
import HDmedi.Server.fcm.dto.FirebaseTokenResponse;
import HDmedi.Server.fcm.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class FirebaseController {
    private final FirebaseService firebaseService;
    @PostMapping
    public ResponseEntity<FCMNotificationResponseDto> sendNotification(@RequestBody FCMNotificationRequestDto request) {
        firebaseService.sendNotificationByToken(request);
        FCMNotificationResponseDto response = FCMNotificationResponseDto.of(request.getTargetUserId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/token")
    public ResponseEntity<FirebaseTokenResponse> patchFCMToken(@RequestBody FirebaseTokenRequest request) {
        FirebaseTokenResponse response = firebaseService.patchToken(request);
        return ResponseEntity.created(URI.create("/api/notification/token")).body(response);
    }
}
