package HDmedi.Server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionContext implements ExceptionContext{
    // MEMBER (예시)
    NOT_FOUND_MEMBER_ERROR(HttpStatus.NOT_FOUND, 1000, "해당 회원을 찾을 수 없습니다."),
    INVALID_MEMBER_ID_ERROR(HttpStatus.BAD_REQUEST, 1001, "회원에 대한 유효하지 않은 요청입니다."),
    INVALID_ADD_MEMBER(HttpStatus.BAD_REQUEST, 1003, "이미 존재하는 캐릭터입니다."),
    FIREBASE_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, 1004, "파이어베이스 토큰 오류입니다.");
    // DOMAIN PER EXCEPTIONS ...


    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
