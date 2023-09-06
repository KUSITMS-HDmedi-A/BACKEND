package HDmedi.Server.domain.user.controller;

import HDmedi.Server.domain.user.dto.response.ErrorResponse;
import HDmedi.Server.global.exception.HDmediException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {
    private final int INVALID_PARAMETER_CODE = 400;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInputFieldException(MethodArgumentNotValidException e) {
        FieldError mainError = e.getFieldErrors().get(0);
        String[] errorInfo = Objects.requireNonNull(mainError.getDefaultMessage()).split(":");
        String message = errorInfo[0];
        return ResponseEntity.badRequest().body(new ErrorResponse(INVALID_PARAMETER_CODE, message));
    }

    @ExceptionHandler(HDmediException.class)
    public ResponseEntity<ErrorResponse> handleHDmediException(HDmediException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledException(Exception e, HttpServletRequest request) {
        log.error("UnhandledException: {} {} errMessage={}\n",
                request.getMethod(),
                request.getRequestURI(),
                e.getMessage()
        );
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(9999, "서버와의 접속이 원활하지 않습니다. HDmedi 서비스 팀에 문의 부탁드립니다."));
    }
}
