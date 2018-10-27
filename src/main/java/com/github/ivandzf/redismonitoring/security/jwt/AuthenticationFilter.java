package com.github.ivandzf.redismonitoring.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (27 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TokenAuthenticationService.getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
        chain.doFilter(request, response);
    }

}
