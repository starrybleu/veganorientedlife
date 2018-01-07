package kr.veganoriented.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by terrylee on 2017. 12. 2..
 */

@Controller
@RequestMapping("/")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        logger.debug("index page");
        return "index";
    }

    @GetMapping("/users/sign-up")
    public String signUp() {
        logger.debug("sign-up page");
        return "users/sign-up";
    }

}
