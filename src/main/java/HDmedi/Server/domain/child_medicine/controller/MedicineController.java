package HDmedi.Server.domain.child_medicine.controller;


import HDmedi.Server.domain.child_medicine.dto.request.EnrollMedicineRequestDto;
import HDmedi.Server.domain.child_medicine.service.MedicineService;
import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.global.config.security.CustomUser;
import HDmedi.Server.global.config.security.JwtTokenProvider;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/api/medicine")
public class MedicineController {

    private final Logger LOGGER = LoggerFactory.getLogger(MedicineController.class);

    private final MedicineService medicineService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MedicineController(MedicineService medicineService, JwtTokenProvider jwtTokenProvider) {
        this.medicineService = medicineService;
        this.jwtTokenProvider = jwtTokenProvider;
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
    @ApiOperation(value = "약 등록", notes = "유저 약 등록")
    @PostMapping(value = "/enroll-medicine")
    public ResponseDto enrollMedicine(
            @AuthenticationPrincipal CustomUser customUser,
            @ApiParam(value = "요청 해줘", required = true) @Validated @RequestBody EnrollMedicineRequestDto enrollMedicineRequestDto
    )  {


        ResponseDto responseDto = medicineService.enrollMedicine(customUser.getUserId(), enrollMedicineRequestDto);


        LOGGER.info("약 등록 완료");

        return responseDto;
    }



}
