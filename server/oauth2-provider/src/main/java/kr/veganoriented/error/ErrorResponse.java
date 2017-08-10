package kr.veganoriented.error;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Data
public class ErrorResponse {

    private String errorCode;
    private String consumerMessage;
    private String applicationMessage;
    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    public ErrorResponse() {}

    public ErrorResponse(String errorCode, String consumerMessage, String applicationMessage) {
        this.errorCode = errorCode;
        this.consumerMessage = consumerMessage;
        this.applicationMessage = applicationMessage;
    }

}