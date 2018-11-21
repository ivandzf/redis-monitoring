package com.github.ivandzf.redismonitoring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (30 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Controller
@RequestMapping("/api")
public class LogoutController {

    @PostMapping("/logout")
    public void logout() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }

}
