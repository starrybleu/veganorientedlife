package kr.veganoriented.exception;

import kr.veganoriented.error.ErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class BaseWebApplicationException extends WebApplicationException {

    private final int status;
    private final String errorMessage;
    private final String developerMessage;

    public BaseWebApplicationException(int httpStatus, String errorMessage, String developerMessage) {
        this.status = httpStatus;
        this.errorMessage = errorMessage;
        this.developerMessage = developerMessage;
    }

    @Override
    public Response getResponse() {
        return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity(getErrorResponse()).build();
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public ErrorResponse getErrorResponse() {
        ErrorResponse response = new ErrorResponse();
        response.setApplicationMessage(developerMessage);
        response.setConsumerMessage(errorMessage);
        return response;
    }

}
