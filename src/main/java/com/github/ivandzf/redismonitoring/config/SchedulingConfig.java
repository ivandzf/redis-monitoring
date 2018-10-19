package com.github.ivandzf.redismonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (17 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Bean
    public TaskScheduler threadPoolExecutor() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(1);
        return threadPoolTaskScheduler;
    }

}
