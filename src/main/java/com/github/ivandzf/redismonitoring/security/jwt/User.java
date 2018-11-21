package com.github.ivandzf.redismonitoring.security.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (27 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String username;
    String password;

}
