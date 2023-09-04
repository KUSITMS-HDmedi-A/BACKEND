package HDmedi.Server.global.exception.badrequest;

import HDmedi.Server.global.exception.ExceptionContext;
import HDmedi.Server.global.exception.HDmediException;
import lombok.Getter;

@Getter
public class BadRequestException extends HDmediException {
    public BadRequestException(ExceptionContext context){
        super(context.getHttpStatus(), context.getMessage(), context.getCode());
    }
}
