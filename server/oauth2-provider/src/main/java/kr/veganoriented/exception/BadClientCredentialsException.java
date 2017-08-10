package kr.veganoriented.exception;

import kr.veganoriented.error.ErrorResponse;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class BadClientCredentialsException extends ErrorResponse {

    public BadClientCredentialsException(String errorMessage) {
        super("401", "Client Credentials were incorrect", "Client Credentials were incorrect. Useage: Base64Encode(username:password) ");
    }
}
