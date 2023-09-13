package HDmedi.Server.domain.alarm.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineAddRequestDto {

    private String character;
    private String medicine;

    private LocalDate startDate;
    private LocalDate endDate;

    private Time time;

    private String[] day;

    private String label;

}
