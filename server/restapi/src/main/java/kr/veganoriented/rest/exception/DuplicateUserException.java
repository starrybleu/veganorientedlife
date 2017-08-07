package kr.veganoriented.rest.exception;

import kr.veganoriented.exception.BaseWebApplicationException;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class DuplicateUserException extends BaseWebApplicationException {

    public DuplicateUserException() {
        super(409, "User already exists", "An attempt was made to create a user that already exists");
    }
}