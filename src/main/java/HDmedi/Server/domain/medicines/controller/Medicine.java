package HDmedi.Server.domain.medicines.controller;

import HDmedi.Server.domain.child_medicine.controller.ChildMedicineController;
import HDmedi.Server.domain.child_medicine.dto.response.MedicineManageResponseDto;

import HDmedi.Server.domain.medicines.service.MedicineService;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.global.config.security.CustomUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/medicine")
public class Medicine {
    private final Logger LOGGER = LoggerFactory.getLogger(ChildMedicineController.class);

    private final MedicineService medicineService;


    @Autowired
    public Medicine(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


}
