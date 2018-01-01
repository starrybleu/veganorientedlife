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
        logger.info("인덱스 페이지");
        return "index";
    }

    @GetMapping("/users/sign-up")
    public String signUp() {
        logger.info("회원가입 페이지");
        return "users/sign-up";
    }

}
