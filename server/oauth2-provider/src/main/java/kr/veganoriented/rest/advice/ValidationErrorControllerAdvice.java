package kr.veganoriented.rest.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by terrylee on 17. 8. 2.
 */

@ControllerAdvice("kr.veganoriented.rest.controller")
public class ValidationErrorControllerAdvice {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    VndErrors userNotFoundExceptionHandler(MethodArgumentNotValidException ex) {
        return new VndErrors("validation_error", errorMessage(ex.getBindingResult().getFieldError()));
    }

    private String errorMessage(FieldError fieldError) {
        return errorMessage(fieldError.getField(), fieldError.getRejectedValue());
    }

    private String errorMessage(String field, Object value) {

        return String.format("Field [%s] validation failed : rejected value [%s]",
                field, value);

    }

}
