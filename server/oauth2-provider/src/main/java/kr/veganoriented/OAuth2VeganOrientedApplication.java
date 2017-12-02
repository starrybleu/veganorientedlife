package kr.veganoriented;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by terrylee on 17. 7. 31.
 */

@EnableAuthorizationServer
@SpringBootApplication
public class OAuth2VeganOrientedApplication {

    public static void main(String[] args) {

        SpringApplication.run(OAuth2VeganOrientedApplication.class, args);

    }

}
