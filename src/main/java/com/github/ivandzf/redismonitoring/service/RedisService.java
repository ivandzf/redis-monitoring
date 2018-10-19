package com.github.ivandzf.redismonitoring.service;

import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
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

    private final RedisCommands<String, String> redisCommands;

    public RedisService(RedisCommands<String, String> redisCommands) {
        this.redisCommands = redisCommands;
    }

    public String getRedisPing() {
        return redisCommands.ping();
    }

    public Map<String, ?> getRedisInformation() {
        return Arrays.stream(redisCommands
                .info()
                .split("# "))
                .filter(s -> !s.equals(""))
                .collect(Collectors.toMap(o -> o.substring(0, o.indexOf("\r\n")),
                        o -> Arrays.stream(o.substring(o.indexOf("\r\n"))
                                .split("\r\n"))
                                .filter(s -> s != null
                                        && !s.equals("")
                                        && s.split(":").length > 1
                                        && !s.contains("process_id")
                                        && !s.contains("tcp_port")
                                        && !s.contains("run_id")
                                        && !s.contains("executable"))
                                .collect(Collectors.toMap(
                                        o1 -> o1.split(":")[0],
                                        o1 -> o1.contains("=") ?
                                                Arrays.stream(o1.split(":")[1]
                                                        .split(","))
                                                        .collect(Collectors.toMap(
                                                                o2 -> o2.split("=")[0],
                                                                o2 -> o2.split("=")[1])) :
                                                o1.split(":")[1]
                                ))
                ));
    }

}
