package HDmedi.Server.domain.alarm.controller;

import HDmedi.Server.domain.alarm.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alarm.dto.request.MedicineAddRequestDto;
import HDmedi.Server.domain.alarm.dto.response.MedicineAddPageResponseDto;
import HDmedi.Server.domain.alarm.service.AlramService;
import HDmedi.Server.domain.child_medicine.controller.ChildMedicineController;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.global.config.security.CustomUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/api/alarm")
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
            @ApiParam(value = "요청 해줘", required = true)  @Validated @RequestBody EnrollRecordRequestDto enrollRecordRequestDto
    )  {

        ResponseDto responseDto = alramService.enrollRecord(customUser.getUserId(), enrollRecordRequestDto);

        LOGGER.info("데이터 전송 완료");

        return responseDto;
    }

    @ApiImplicitParam(
            name = "access",
            value = "accessToken",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer your-access-token"
    )
    @ApiResponse(code = 200, message = "Success", response = MedicineAddPageResponseDto.class)
    @ApiOperation(value = "알람 추가 랜더링", notes = "알람 추가 페이지 랜더링 시 데이터 API")

    @GetMapping(value = "/add-page")

    public MedicineAddPageResponseDto medicineAddPage(
            @AuthenticationPrincipal CustomUser customUser
    )  {

       MedicineAddPageResponseDto medicineAddPageResponseDto = alramService.medicineAddPage(customUser.getUserId());
        LOGGER.info("데이터 전송 완료");

        return medicineAddPageResponseDto;
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
    @ApiOperation(value = "알람 추가 ", notes = "알람 추가 API")

    @PostMapping(value = "/add")

    public ResponseDto medicineAdd(
            @AuthenticationPrincipal CustomUser customUser,
            @ApiParam(value = "요청 해줘", required = true) @Validated @RequestBody MedicineAddRequestDto medicineAddRequest
    ) throws ParseException {

        ResponseDto responseDto = alramService.medicineAdd(customUser.getUserId(), medicineAddRequest);


        LOGGER.info("알림 추가 완료");

        return responseDto;
    }
}
