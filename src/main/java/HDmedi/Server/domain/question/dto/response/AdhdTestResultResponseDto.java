package HDmedi.Server.domain.question.dto.response;


import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdhdTestResultResponseDto extends ResponseDto {

    int score;
    String result;
}
