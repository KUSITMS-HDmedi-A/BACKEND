package HDmedi.Server.domain.child_medicine.service;

import HDmedi.Server.domain.child_medicine.dto.request.EnrollMedicineRequestDto;
import HDmedi.Server.domain.child_medicine.dto.response.DoseRecordResponseDto;
import HDmedi.Server.domain.child_medicine.dto.response.MedicineManageResponseDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;

import java.time.LocalDate;

public interface ChildMedicineService {

    ResponseDto enrollMedicine(Long userId, EnrollMedicineRequestDto enrollMedicineRequestDto);

    MedicineManageResponseDto selectMedicineManage(Long userId);

    DoseRecordResponseDto doseRecord(Long userId, LocalDate today);
}
