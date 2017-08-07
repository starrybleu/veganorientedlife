package kr.veganoriented.rest.exception;

import kr.veganoriented.exception.BaseWebApplicationException;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class AuthenticationException extends BaseWebApplicationException {

    public AuthenticationException() {
        super(401, "Authentication Error", "Authentication Error. The username or password were incorrect");
    }
}