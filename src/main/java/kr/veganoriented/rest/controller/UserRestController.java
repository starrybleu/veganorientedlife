package kr.veganoriented.rest.controller;

import kr.veganoriented.domain.User;
import kr.veganoriented.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by terrylee on 17. 8. 1.
 */

@RestController
@RequestMapping("/api/oauth/users")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    public static final String OWNER = "authentication.name == #userName";
    public static final String ADMIN = "hasRole('ADMIN')";

    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    public Map<String, Object> signupUser(@Validated @RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        LOG.info("user =>>> " + user.toString());
        User savedUser = userService.save(user);
//        createTokenForNewUser(savedUser.getId(), user.getHashedPassword(), "foo");
        resultMap.put("status", "success for save");
        resultMap.put("message", savedUser.getEmailAddress() + ", Welcome to Vegan Oriented");

        return resultMap;
    }

    @Autowired
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }
}
