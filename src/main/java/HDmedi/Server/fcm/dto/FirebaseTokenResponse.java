package HDmedi.Server.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseTokenResponse {
    Long Id;

    public static FirebaseTokenResponse of(Long userId) {
        return new FirebaseTokenResponse(userId);
    }
}
