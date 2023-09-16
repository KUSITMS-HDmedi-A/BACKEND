package HDmedi.Server.fcm.controller;

import HDmedi.Server.fcm.dto.FCMNotificationRequestDto;
import HDmedi.Server.fcm.dto.FCMNotificationResponseDto;
import HDmedi.Server.fcm.dto.FirebaseTokenRequest;
import HDmedi.Server.fcm.dto.FirebaseTokenResponse;
import HDmedi.Server.fcm.service.FirebaseService;
import HDmedi.Server.global.config.security.CustomUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class FirebaseController {
    private final FirebaseService firebaseService;

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = FCMNotificationResponseDto.class)
    @ApiOperation(value = "FCM 푸시 알림")
    @PostMapping
    public ResponseEntity<FCMNotificationResponseDto> sendNotification(@RequestBody FCMNotificationRequestDto request) {
        firebaseService.sendNotificationByToken(request);
        FCMNotificationResponseDto response = FCMNotificationResponseDto.of(request.getTargetUserId());
        return ResponseEntity.ok(response);
    }

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 201, message = "Created", response = FirebaseTokenResponse.class)
    @ApiOperation(value = "FCM 토큰 저장")
    @PatchMapping("/token")
    public ResponseEntity<FirebaseTokenResponse> patchFCMToken(
            @AuthenticationPrincipal CustomUser customUser,
            @RequestBody FirebaseTokenRequest request) {
        Long userId = customUser.getUserId();
        FirebaseTokenResponse response = firebaseService.patchToken(request, userId);
        return ResponseEntity.created(URI.create("/api/notification/token")).body(response);
    }
}
