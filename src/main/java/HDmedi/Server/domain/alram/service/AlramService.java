package HDmedi.Server.domain.alram.service;


import HDmedi.Server.domain.alram.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AlramService {

    ResponseDto enrollRecord(Long userId, EnrollRecordRequestDto enrollRecordRequestDto);
}
