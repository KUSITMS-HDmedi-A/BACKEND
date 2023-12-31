package HDmedi.Server.domain.child_medicine.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
public class MedicineManageResponseDto extends ResponseDto {

    private List<CharacterDTO> characterList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class CharacterDTO {
        private String characterName;
        private List<EnrollMedicineDTO> enrollMedicineList;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class EnrollMedicineDTO {
        private String purpose;
        private String startDate;
        private String endDate;
        private int medicineCount;
        private List<MedicineDTO> medicineList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
     public static class MedicineDTO {
        private String name;
        private List<String> effectList;
        private String derections;
    }

//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @Setter
//    @Builder
//    public static class EffectDTO {
//
//        private String effect;
//    }
}

