package HDmedi.Server.domain.alarm.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EnrollRecordRequestDto {

    @NotBlank
    private String character;
}
