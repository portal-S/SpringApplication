package com.portal.springrest.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final UserDetailsService detailsService;

    public JwtTokenProvider(@Qualifier("accServImpl") UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long validMS;

    @Value("${jwt.header}")
    private String authHeader;

    @PostConstruct
    public void init(){
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(String username, String role){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date valid = new Date(now.getTime() + validMS * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(valid)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthException("JWT token invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuth(String token){
        UserDetails userDetails = detailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserName(String token){
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader(authHeader);
    }
}
