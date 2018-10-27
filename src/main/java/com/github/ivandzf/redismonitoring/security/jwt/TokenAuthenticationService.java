package com.github.ivandzf.redismonitoring.security.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    static void addAuthentication(HttpServletResponse httpServletResponse, String username, Collection<? extends GrantedAuthority> role) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .claim("roles", role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        httpServletResponse.addCookie(new Cookie("token", jwt));
    }

    @SuppressWarnings({"unchecked", "NullPointer"})
    static Authentication getAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String token = httpServletRequest.getHeader(HEADER_STRING);

        if (token != null){
            Claims claims;
            try {
                 claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (JwtException e) {
                if (e instanceof ExpiredJwtException) {
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is expired");
                } else {
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                }

                return null;
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            ArrayList<LinkedHashMap<String, String>> authorities = Objects.requireNonNull(claims).get("roles", ArrayList.class);
            authorities.forEach(stringStringLinkedHashMap -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(stringStringLinkedHashMap.get("authority")));
            });

            return new UsernamePasswordAuthenticationToken(claims.get("sub"), null, grantedAuthorities);
        }

        return null;
    }


}
