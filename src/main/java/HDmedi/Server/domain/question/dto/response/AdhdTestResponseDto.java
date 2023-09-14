package HDmedi.Server.domain.question.dto.response;

import HDmedi.Server.domain.question.entity.Question;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdhdTestResponseDto extends ResponseDto {


    List<Questions> questionsList;

    String[] character;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Questions{

        private String description;
        private String blue;

    }


}
