package com.demo.testGraphql.security.jwt;

import com.demo.testGraphql.models.entities.User;
import com.demo.testGraphql.repositories.UserRepository;
import com.demo.testGraphql.security.JwtUser;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.prefix}")
    private String prefix;

    private String AUTHORITIES = "authorities";

    @Autowired
    private UserRepository userRepository;

    public String getUsernameFromToken(String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith(prefix)) {
            return "";
        }

        // Remove prefix token
        var token = jwtToken.replace(prefix, "");
        // Acquire token claims
        var claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        // Setup new authentication to access resources
        var username = claims.getSubject();
        return username;
    }

    public String generateToken(Authentication authentication) {
        var now = System.currentTimeMillis();
        var token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES, authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();

        return prefix + token;
    }

    public User getUserCurrent() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        }
        Authentication authentication = securityContext.getAuthentication();
        String username = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication != null && authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        } else {
            return null;
        }
        Optional<User> user = userRepository.findTopByUsername(username);
        return user.orElse(null);
    }

    public Boolean isTokenValid(String jwtAuthToken, UserDetails userDetails) {
        if (jwtAuthToken == null || !jwtAuthToken.startsWith(prefix)) {
            return false;
        }
        // Remove prefix token
        var token = jwtAuthToken.replace(prefix, "");

        // Acquire token claims
        var claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        // Setup new authentication to access resources
        var username = claims.getSubject();
        JwtUser user = (JwtUser) userDetails;
        return username.equals(user.getUsername());
    }

}
