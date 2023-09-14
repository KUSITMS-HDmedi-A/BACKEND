package HDmedi.Server.domain.alarm.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalTime time;

    private String[] day;
    @NotBlank
    private String label;

}
