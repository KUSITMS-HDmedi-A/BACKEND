package HDmedi.Server.global.exception.notfound;

import HDmedi.Server.global.exception.ExceptionContext;
import HDmedi.Server.global.exception.HDmediException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends HDmediException {
    public NotFoundException(ExceptionContext context) {
        super(context.getHttpStatus(), context.getMessage(), context.getCode());
    }


}
