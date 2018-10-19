package com.github.ivandzf.redismonitoring.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (16 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisCommands<String, String> redisCommands() {
        RedisURI redisURI = RedisURI
                .builder()
                .withHost("redis-15962.c10.us-east-1-2.ec2.cloud.redislabs.com")
                .withPort(15962)
                .withPassword("HRP9A9DzNK0k0bVI5ZzLcBJcXtazP7mo")
                .build();

        RedisClient redisClient = RedisClient.create(redisURI);
        redisClient.setOptions(ClientOptions.builder()
                .autoReconnect(true)
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(30L)))
                .build());
        return redisClient.connect().sync();
    }

}
