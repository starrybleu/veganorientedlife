package kr.veganoriented.rest.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.security.Principal;

import static org.springframework.util.Assert.notNull;

/**
 * Created by terrylee on 17. 8. 4.
 */

@Data
public class ApiUser implements Principal {

    @Email
    @NotNull
    private String emailAddress;
    private String firstName;
    private String lastName;
    private Integer age;
    private String id;

    public ApiUser() {
    }

    public ApiUser(User user) {
        this.emailAddress = user.getEmailAddress();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.id = user.getId();
        this.age = user.getAge();
    }

    public void setEmailAddress(final String emailAddress) {
        notNull(emailAddress, "Mandatory argument 'emailAddress missing.'");
        this.emailAddress = emailAddress;
    }

    public void setId(final String id) {
        notNull(id, "Mandatory argument 'id missing.'");
        this.id = id;
    }

    public void setFirstName(final String firstName) {
        notNull(firstName, "Mandatory argument 'firstName missing.'");
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        notNull(lastName, "Mandatory argument 'lastName missing.'");
        this.lastName = lastName;
    }

    @Override
    public String getName() {
        return this.getEmailAddress();
    }

}
