package com.github.ivandzf.redismonitoring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (16 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
