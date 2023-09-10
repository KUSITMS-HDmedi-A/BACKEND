package HDmedi.Server.domain.user_child.service;

import HDmedi.Server.domain.user_child.dto.request.NewChildRequestDto;
import HDmedi.Server.domain.user_child.dto.response.HomeResponseDto;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;

public interface ChildService {

    HomeResponseDto homePage (Long usesrId);

    ResponseDto enrollChild(Long userId, NewChildRequestDto newChildRequestDto);
}
