package kr.veganoriented.rest.controller;

import kr.veganoriented.domain.User;
import kr.veganoriented.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * Created by terrylee on 17. 8. 1.
 */

@RestController
@RequestMapping("/api/oauth/users")
public class UserRestController {

    public static final String OWNER = "authentication.name == #userName";
    public static final String ADMIN = "hasRole('ADMIN')";

    private UserService userService;

    @RequestMapping("authorization-code")
    public String authorizationCode(@RequestParam("code") String code) {
        String strForCurl = "curl " +
                "-F \"grant_type=authorization_code\" " +
                "-F \"code=%s\" " +
                "-F \"scope=read\" " +
                "-F \"client_id=foo\" " +
                "-F \"client_secret=bar\" " +
                "-F \"redirect_uri=http://localhost:8080/api/oauth/users/authorization-code\" " +
                "\"http://foo:bar@localhost:8080/oauth/token\"";

        String curl = String.format(strForCurl, code);

        return curl;
    }

    @PreAuthorize(ADMIN + " or " + OWNER)
    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public User getUser(@PathVariable String emailAddress) {

        User user = userService.findByEmailAddress(emailAddress);

//        if(user == null) {
//            return user;
//        }

        return user;
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@Validated @RequestBody User user) {
        System.out.println("user =>>> " + user.toString());

        User savedUser = userService.save(user);

        return savedUser;
    }

    @Autowired
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }
}
