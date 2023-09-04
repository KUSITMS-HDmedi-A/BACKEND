package HDmedi.Server.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionContext {
    HttpStatus getHttpStatus();

    String getMessage();

    int getCode();
}
