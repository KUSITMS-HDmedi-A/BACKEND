package HDmedi.Server.global.exception.notfound;

import static HDmedi.Server.global.exception.CustomExceptionContext.NOT_FOUND_ALARM_ERROR;

public class NotFoundAlarmException extends NotFoundException {
    public NotFoundAlarmException() {
        super(NOT_FOUND_ALARM_ERROR);
    }
}
