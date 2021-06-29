package com.budan.springappblog.security.service;

import antlr.StringUtils;
import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.security.exception.ExpiredTokenAuthenticationException;
import com.budan.springappblog.security.exception.InvalidTokenAuthenticationException;
import com.budan.springappblog.security.model.JwtAuthenticationToken;
import com.budan.springappblog.security.model.JwtUserDetails;
import com.budan.springappblog.security.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(final Authentication authRequest) throws AuthenticationException {
        String token = StringUtils.strip((String) authRequest.getCredentials());

        Token tokenLoad = authenticationHelper.decodeToken(token);

        checkIsExpired(tokenLoad.getExp());

        Integer userEntityId = tokenLoad.getUserId();
        if(Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthenticationException("Token doesn't contain a user id");
        }

        Optional<User> user = userRepository.findById(userEntityId);
        if(Objects.isNull(user)) {
            throw new InvalidTokenAuthenticationException("Token doesn't contain existed user id");
        }

        JwtUserDetails userDetails = new JwtUserDetails(user.get());
        return new JwtAuthenticationToken(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
