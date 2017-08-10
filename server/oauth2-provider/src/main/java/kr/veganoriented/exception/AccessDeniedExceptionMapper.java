package kr.veganoriented.exception;

import kr.veganoriented.error.ErrorResponse;

import org.springframework.security.access.AccessDeniedException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Override
    public Response toResponse(AccessDeniedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorResponse("401", "You do not have the appropriate privileges to access this resource",
                        exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).
                        build();
    }
}
