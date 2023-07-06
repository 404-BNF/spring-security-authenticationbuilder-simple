package dev.sk.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurityAuthenticationbuilderSimpleApplication {

    @Configuration
    @EnableWebSecurity
    class AuthenticationConfig {
        /**
         *
         * @param httpSecurity
         * @return
         * @throws Exception
         * Username: admin
         * Password: 12345
         */

        @Bean
        public AuthenticationManager authentication(HttpSecurity httpSecurity) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.inMemoryAuthentication()
                                        .withUser("admin")
                                        .password("$2a$10$0./AWB/y0/LS1Qr5dgk/U.tsLHZnRj8vcX5WCJjfWfSdNwV1kOYqO")
                                        .authorities("read")
                                        .and()
                                        .passwordEncoder(new BCryptPasswordEncoder());
            return authenticationManagerBuilder.build();
        }
    }
    @RestController
    class MainController {
        @RequestMapping("/")
        public String home(){
            return "Hello Spring Boot...";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAuthenticationbuilderSimpleApplication.class, args);
    }

}
