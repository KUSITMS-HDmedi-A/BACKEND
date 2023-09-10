package HDmedi.Server.domain.child_medicine.service;

import HDmedi.Server.domain.child_medicine.dto.request.EnrollMedicineRequestDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;

public interface MedicineService {

    ResponseDto enrollMedicine(Long userId, EnrollMedicineRequestDto enrollMedicineRequestDto);
}
