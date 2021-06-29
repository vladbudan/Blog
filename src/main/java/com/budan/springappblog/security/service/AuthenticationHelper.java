package com.budan.springappblog.security.service;

import com.budan.springappblog.security.exception.InvalidTokenAuthenticationException;
import com.budan.springappblog.security.model.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationHelper {

    public static final String AUTHENTICATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_PARAM = "auth";
    private final String SECRET_KEY = "SecretKey";
    private final ObjectMapper objectMapper;

    private Long tokenExpirationTime = 36000000L; // one hour

    public String generateToken(final Integer userId) {
        try {
            Token tokenLoad = new Token(
                    userId,
                    Instant.now().getEpochSecond() + this.tokenExpirationTime
            );

            String token = this.objectMapper.writeValueAsString(tokenLoad);
            return JwtHelper.encode(token, new MacSigner(SECRET_KEY)).getEncoded();
        } catch(JsonProcessingException exception) {
            throw new InternalAuthenticationServiceException("Error generating token", exception);
        }
    }

    public Token decodeToken(final String token) {
        if(Objects.isNull(token)) {
            throw new InvalidTokenAuthenticationException("Token was null or blank");
        }

        Jwt jwt = JwtHelper.decode(token);

        try {
            jwt.verifySignature(new MacSigner(SECRET_KEY));
        } catch (Exception exception) {
            throw new InvalidTokenAuthenticationException("Token signature verification failed ", exception);
        }

        String claims = jwt.getClaims();
        Token tokenPayLoad;
        try {
            tokenPayLoad = this.objectMapper.readValue(claims, Token.class);
        } catch (IOException exception) {
            throw new InvalidTokenAuthenticationException("Token parsing failed ", exception);
        }

        return tokenPayLoad;
    }

}
