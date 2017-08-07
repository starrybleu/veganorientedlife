package kr.veganoriented.rest.exception;

import kr.veganoriented.exception.BaseWebApplicationException;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class TokenHasExpiredException extends BaseWebApplicationException {

    public TokenHasExpiredException() {
        super(403, "Token has expired", "An attempt was made to load a token that has expired");
    }
}
