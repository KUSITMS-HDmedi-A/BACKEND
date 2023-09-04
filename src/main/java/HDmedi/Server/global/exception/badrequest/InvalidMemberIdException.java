package HDmedi.Server.global.exception.badrequest;

import static HDmedi.Server.global.exception.CustomExceptionContext.INVALID_MEMBER_ID_ERROR;

public class InvalidMemberIdException extends BadRequestException {
    public InvalidMemberIdException(){
        super(INVALID_MEMBER_ID_ERROR);
    }
}
