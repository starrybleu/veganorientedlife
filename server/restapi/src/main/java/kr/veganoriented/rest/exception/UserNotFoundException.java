package kr.veganoriented.rest.exception;

import kr.veganoriented.exception.BaseWebApplicationException;

/**
 * Created by terrylee on 17. 8. 2.
 */

public class UserNotFoundException extends BaseWebApplicationException {

    public UserNotFoundException() {
        super(404, "User Not Found", "No User could be found for that Id");
    }

}