package com.github.ivandzf.redismonitoring.security.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * redis-monitoring
 *
 * @author Divananda Zikry Fadilla (27 October 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 1800 * 1000;
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";
    private static final Gson gson = new Gson();

    static void addAuthentication(HttpServletResponse httpServletResponse, Authentication authentication) {
        String jwt = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        httpServletResponse.addCookie(new Cookie("token", jwt));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    static void getAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String token = httpServletRequest.getHeader(HEADER_STRING);

        if (token != null) {
            try {
                Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (JwtException e) {
                if (e instanceof ExpiredJwtException) {
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is expired");
                } else {
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                }
            }
        }
    }


}
