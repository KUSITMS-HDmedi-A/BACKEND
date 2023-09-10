package HDmedi.Server.domain.user_child.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NewChildRequestDto {

    @NotBlank
    public String name;

    @NotBlank
    public int age;

    @NotBlank
    public String relationship;
}
