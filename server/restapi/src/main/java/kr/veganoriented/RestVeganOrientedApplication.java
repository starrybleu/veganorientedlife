package kr.veganoriented;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by terrylee on 17. 7. 31.
 */

@EnableResourceServer
@SpringBootApplication
public class RestVeganOrientedApplication extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.authorizeRequests()
                .anyRequest()
                .permitAll()
                .antMatchers("/api/oauth/**").access("#oauth2.hasScope('read')")
                .anyRequest().authenticated();
    }

    public static void main(String[] args) {

        SpringApplication.run(RestVeganOrientedApplication.class, args);

    }



}
