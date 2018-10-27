package com.github.ivandzf.redismonitoring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (16 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @GetMapping("/api/test")
    public String test(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }

}
