package HDmedi.Server.domain.alram.controller;

import HDmedi.Server.domain.alram.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alram.service.AlramService;
import HDmedi.Server.domain.child_medicine.controller.ChildMedicineController;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.global.config.security.CustomUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/alram")
public class AlramController {
    private final Logger LOGGER = LoggerFactory.getLogger(ChildMedicineController.class);

    private final AlramService alramService;

    @Autowired
    public AlramController(AlramService alramService) {
        this.alramService = alramService;
    }





    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = ResponseDto.class)
    @ApiOperation(value = "복용 기록 ", notes = "복용 기록 페이지 레코드 등록")
    @PostMapping(value = "/dosage-record")
    public ResponseDto enrollRecord(
            @AuthenticationPrincipal CustomUser customUser,
            @Validated @RequestBody EnrollRecordRequestDto enrollRecordRequestDto
    )  {

        ResponseDto responseDto = alramService.enrollRecord(customUser.getUserId(), enrollRecordRequestDto);

        LOGGER.info("데이터 전송 완료");

        return responseDto;
    }
}
