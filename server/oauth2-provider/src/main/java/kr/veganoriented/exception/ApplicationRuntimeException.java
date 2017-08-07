package kr.veganoriented.exception;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class ApplicationRuntimeException extends BaseWebApplicationException {

    public ApplicationRuntimeException(String applicationMessage) {
        super(500, "Internal System error", applicationMessage);
    }
}
