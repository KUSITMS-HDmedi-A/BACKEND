package HDmedi.Server.domain.user_entity.controller;


import HDmedi.Server.domain.user_entity.dto.response.GetFamilyDetails;
import HDmedi.Server.domain.user_entity.dto.response.GetFamilyInfo;
import HDmedi.Server.domain.user_entity.dto.response.GetUserChildDetails;
import HDmedi.Server.domain.user_entity.dto.response.GetUserDetails;
import HDmedi.Server.domain.user_entity.service.UserService;
import HDmedi.Server.global.config.security.CustomUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = GetUserDetails.class)
    @ApiOperation(value = "유저 상세 정보 조회")
    @GetMapping("/details")
    public ResponseEntity<GetUserDetails> getUserDetails(@AuthenticationPrincipal CustomUser customUser) {
        GetUserDetails response = userService.getUserDetails(customUser.getUserId());
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
    @ApiResponse(code = 200, message = "Success", response = GetUserChildDetails.class)
    @ApiOperation(value = "유저 자식 상세 정보 조회")
    @GetMapping("/{child-id}/details")
    public ResponseEntity<GetUserChildDetails> getUserChildDetails(
            @AuthenticationPrincipal CustomUser customUser,
            @PathVariable(name = "child-id") Long childId) {
        GetUserChildDetails response = userService.getUserChildDetails(childId);
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
    @ApiResponse(code = 200, message = "Success", response = GetFamilyDetails.class)
    @ApiOperation(value = "구성원 알림 정보 상세 조회")
    @GetMapping("/alarms")
    public ResponseEntity<GetFamilyDetails> getUserChildAlarms(
            @AuthenticationPrincipal CustomUser customUser) {
        GetFamilyDetails response = userService.getUserChildAlarms(customUser.getUserId());
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
    @ApiResponse(code = 200, message = "Success", response = GetFamilyInfo.class)
    @ApiOperation(value = "구성원 알림 정보 간단 조회")
    @GetMapping("/family")
    public ResponseEntity<GetFamilyInfo> getFamilyInfo(
            @AuthenticationPrincipal CustomUser customUser) {
        GetFamilyInfo response = userService.getFamilyInfo(customUser.getUserId());
        return ResponseEntity.ok(response);
    }
}
