package HDmedi.Server.domain.alarm.dto.response;

import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor

@SuperBuilder
public class MedicineAddPageResponseDto extends ResponseDto {


    private List<CharacterDto> characterData;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class CharacterDto{
        private String characterName;
        private List<MedicineDto> medicine;


    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class MedicineDto{

        private String purpose;
    }

}
