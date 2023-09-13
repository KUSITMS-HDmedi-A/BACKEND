package HDmedi.Server.domain.alarm.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineAddPageResponseDto extends ResponseDto {

    private String[] character;
    private String[] medicine;


}
