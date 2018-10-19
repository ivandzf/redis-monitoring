package com.github.ivandzf.redismonitoring.task;

import com.github.ivandzf.redismonitoring.service.RedisService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (17 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Component
public class TaskExecutor {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RedisService redisService;
    private final Gson gson = new Gson();

    public TaskExecutor(SimpMessagingTemplate simpMessagingTemplate, RedisService redisService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.redisService = redisService;
    }

    @Scheduled(fixedRate = 1000)
    public void doIt() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", gson.toJsonTree(redisService.getRedisInformation()));
        jsonObject.addProperty("dateTime", new SimpleDateFormat("dd/MM/YYYY hh:mm:ss:SS").format(new Date()));
        this.simpMessagingTemplate.convertAndSend("/redis", gson.toJson(jsonObject));
    }

}
