package HDmedi.Server.domain.child_medicine.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.*;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
        private String characterName;
        private String startDate;
        private String endDate;
        private List<MedicineDTO> medicineList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
     public static class MedicineDTO {
        private String name;
        private List<TagDTO> tagList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TagDTO {

        private String tag;
    }




}

