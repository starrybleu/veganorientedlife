package kr.veganoriented.rest.advice;

import kr.veganoriented.rest.exception.UserNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by terrylee on 17. 8. 2.
 */

@ControllerAdvice("kr.veganoriented.rest.controller")
public class RestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setMessage(e.getMessage());
        return errorBean;
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors userNotFoundExceptionHandler(UserNotFoundException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    class ErrorBean {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
