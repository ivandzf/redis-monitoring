package com.github.ivandzf.redismonitoring.controller;

import com.github.ivandzf.redismonitoring.service.RedisService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (09 January 2019)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Controller
public class DashboardController {

    private final RedisService redisService;

    public DashboardController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping(value = "/redis/info", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Mono<?>>> getRedisInformation() {
        return Flux.interval(Duration.ofSeconds(1))
                .log()
                .map(sequence -> ServerSentEvent.<Mono<?>>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data(redisService.getRedisInformation())
                        .build());
    }

}
