package HDmedi.Server.global.exception.badrequest;

import static HDmedi.Server.global.exception.CustomExceptionContext.FIREBASE_CONFIG_EXCEPTION;

public class FirebaseConfigException extends BadRequestException {
    public FirebaseConfigException(){
        super(FIREBASE_CONFIG_EXCEPTION);
    }
}