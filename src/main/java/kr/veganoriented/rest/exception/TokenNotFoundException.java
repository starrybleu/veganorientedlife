package kr.veganoriented.rest.exception;

import kr.veganoriented.exception.BaseWebApplicationException;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class TokenNotFoundException extends BaseWebApplicationException {

    public TokenNotFoundException() {
        super(404, "Token Not Found", "No token could be found for that Id");
    }
}
