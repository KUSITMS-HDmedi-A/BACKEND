package HDmedi.Server.global.exception.unauthorized;

import HDmedi.Server.global.exception.HDmediException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException  extends HDmediException {

    public UnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
}