package com.github.ivandzf.redismonitoring.service;

import com.github.ivandzf.redismonitoring.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (18 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void getRedisInformationTest() {
        Map<String, ?> map = redisService.getRedisInformation();
        Assert.assertNotNull(map);
    }

}
