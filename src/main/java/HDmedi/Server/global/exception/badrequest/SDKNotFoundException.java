package HDmedi.Server.global.exception.badrequest;

import static HDmedi.Server.global.exception.CustomExceptionContext.SDK_NOT_FOUND;

public class SDKNotFoundException extends BadRequestException {
    public SDKNotFoundException() {
        super(SDK_NOT_FOUND);
    }
}