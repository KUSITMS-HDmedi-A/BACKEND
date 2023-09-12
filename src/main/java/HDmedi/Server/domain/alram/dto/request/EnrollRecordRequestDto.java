package HDmedi.Server.domain.alram.dto.request;

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
