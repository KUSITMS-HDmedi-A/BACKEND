package HDmedi.Server.domain.user_entity.controller;


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
import org.springframework.web.bind.annotation.*;

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
}
