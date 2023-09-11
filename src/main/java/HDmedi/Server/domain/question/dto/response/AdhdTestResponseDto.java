package HDmedi.Server.domain.question.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdhdTestResponseDto extends ResponseDto {

    String[] question;

    String[] character;


}
