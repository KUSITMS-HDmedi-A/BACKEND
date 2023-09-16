package HDmedi.Server.global.exception.notfound;

import static HDmedi.Server.global.exception.CustomExceptionContext.NOT_FOUND_MEDICINE_ERROR;

public class NotFoundMedicineException extends NotFoundException {
    public NotFoundMedicineException() {
        super(NOT_FOUND_MEDICINE_ERROR);
    }
}
