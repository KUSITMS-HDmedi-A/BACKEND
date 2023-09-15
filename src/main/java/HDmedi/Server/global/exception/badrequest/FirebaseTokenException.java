package HDmedi.Server.global.exception.badrequest;

import static HDmedi.Server.global.exception.CustomExceptionContext.FIREBASE_TOKEN_EXCEPTION;

public class FirebaseTokenException extends BadRequestException {
    public FirebaseTokenException(){
        super(FIREBASE_TOKEN_EXCEPTION);
    }
}