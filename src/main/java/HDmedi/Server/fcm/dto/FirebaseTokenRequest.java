package HDmedi.Server.fcm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FirebaseTokenRequest {
    String token;
    Long id; // user id
}
