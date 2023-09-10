package HDmedi.Server.domain.child_medicine.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EnrollMedicineRequestDto {

    @NotBlank
    public String purpose;

    @NonNull
    public LocalDate startDate;

    @NonNull
    public LocalDate endDate;

    public String[] medicine;


}
