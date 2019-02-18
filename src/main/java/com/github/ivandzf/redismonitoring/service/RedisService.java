package com.github.ivandzf.redismonitoring.service;

import io.lettuce.core.api.reactive.RedisReactiveCommands;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (16 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Service
public class RedisService {

    private final RedisReactiveCommands<String, String> redisReactiveCommands;

    public RedisService(RedisReactiveCommands<String, String> redisReactiveCommands) {
        this.redisReactiveCommands = redisReactiveCommands;
    }

    public Mono<?> getRedisInformation() {
        return redisReactiveCommands
                .info()
                .map(s -> Flux.fromArray(s.split("# ")).filter(s1 -> !s1.equals("")))
                .flatMap(Flux::collectList)
                .map(strings -> Flux
                        .fromIterable(strings)
                        .toStream()
                        .collect(Collectors.toMap(
                                o -> o.substring(0, o.indexOf("\r\n")),
                                o -> Flux.fromArray(o.substring(o.indexOf("\r\n")).split("\r\n"))
                                        .filter(s -> s != null && !s.equals(""))
                                        .filter(s -> s.split(":").length > 1)
                                        .toStream()
                                        .collect(Collectors.toMap(
                                                o1 -> o1.split(":")[0],
                                                o1 -> o1.contains("=")
                                                        ? Flux.fromArray(o1.split(":")[1].split(","))
                                                        .toStream()
                                                        .collect(Collectors.toMap(
                                                                o2 -> o2.split("=")[0],
                                                                o2 -> o2.split("=")[1]))
                                                        : o1.split(":")[1])
                                        )
                                )
                        )
                );

    }

}
