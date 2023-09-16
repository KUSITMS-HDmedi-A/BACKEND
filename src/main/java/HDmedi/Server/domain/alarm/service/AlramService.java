package HDmedi.Server.domain.alarm.service;


import HDmedi.Server.domain.alarm.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alarm.dto.request.MedicineAddRequestDto;
import HDmedi.Server.domain.alarm.dto.response.MedicineAddPageResponseDto;
import HDmedi.Server.domain.alarm.dto.response.PatchDoseSignResponse;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;

import java.text.ParseException;


public interface AlramService {

    ResponseDto enrollRecord(Long userId, EnrollRecordRequestDto enrollRecordRequestDto);

    MedicineAddPageResponseDto medicineAddPage(Long userId);

    ResponseDto medicineAdd(Long userId, MedicineAddRequestDto medicineAddRequestDto) throws ParseException;

    PatchDoseSignResponse patchDoseSign(Long alarmDateId);
}
