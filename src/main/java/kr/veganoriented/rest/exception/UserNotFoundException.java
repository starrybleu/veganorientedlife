package kr.veganoriented.rest.exception;

/**
 * Created by terrylee on 17. 8. 2.
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userName) {

        super(
                String.format("User %s not found.", userName)
        );

    }

}