package com.mym.consulting.util;

import io.jsonwebtoken.JwtBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class TokenGenerator {

    public static final String JWT_ID = "mymJWT";
    public static final String SECRET = "mymSecretKey";
    public static final String PREFIX = "Bearer ";

    private final Long EXPIRATION = 600000l;

    public String getJWTToken(String username) {
        JwtBuilder builder = Jwts.builder().setId(JWT_ID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512,
                        SECRET.getBytes());
        return PREFIX + builder.compact();
    }

}
