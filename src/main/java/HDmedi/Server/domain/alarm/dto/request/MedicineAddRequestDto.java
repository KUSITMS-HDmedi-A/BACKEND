package HDmedi.Server.domain.alarm.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineAddRequestDto {

    @NotBlank
    private String character;

    @NotBlank
    private String medicine;


    private LocalDate startDate;

    private LocalDate endDate;

    private Time time;

    private String[] day;
    @NotBlank
    private String label;

}
