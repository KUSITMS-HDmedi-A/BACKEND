package HDmedi.Server.global.exception.notfound;

import static HDmedi.Server.global.exception.CustomExceptionContext.NOT_FOUND_MEMBER_ERROR;

public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super(NOT_FOUND_MEMBER_ERROR);
    }
}
