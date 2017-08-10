package kr.veganoriented.error;

import lombok.Data;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Data
public class ValidationError {

    private String propertyName;
    private String propertyValue;
    private String message;

}

