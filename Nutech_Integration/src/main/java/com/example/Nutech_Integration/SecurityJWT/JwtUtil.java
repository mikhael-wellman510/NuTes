package com.example.Nutech_Integration.SecurityJWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Nutech_Integration.Entity.Auth.AppUser;
import com.example.Nutech_Integration.Service.Auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {


    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.appName}")
    private String appName;

    @Value("${app.jwtExpirationInSecond}")
    private long jwtExpirationInSecond;

    private final UserService userService;

    public String generateToken(AppUser appUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));


            return JWT.create()

                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpirationInSecond))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", appUser.getErole().name())
                    .withClaim("username" , appUser.getUsername())
                    .withClaim("userId", appUser.getId())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getIssuer().equals(appName);

        } catch (JWTVerificationException e) {
            throw new RuntimeException();
        }
    }

    public Map<String, String> getUserInfoByToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getClaim("userId").asString());
            userInfo.put("role", decodedJWT.getClaim("role").asString());
            userInfo.put("username", decodedJWT.getClaim("username").asString());

            return userInfo;

        } catch (JWTVerificationException e) {
            throw new RuntimeException();
        }
    }


}
