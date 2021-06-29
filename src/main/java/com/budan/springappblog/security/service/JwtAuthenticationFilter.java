package com.budan.springappblog.security.service;

import com.budan.springappblog.security.handler.RestAuthenticationFailureHandler;
import com.budan.springappblog.security.model.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(request -> true);
        setAuthenticationManager(authenticationManager);    
        setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            String token = Optional.ofNullable(request.getHeader((AuthenticationHelper.AUTHENTICATION_HEADER)))
                    .map(header -> header.substring(7)).orElse(null);

            if(Objects.isNull(token)) {
                throw new BadCredentialsException("Token not found in request's header");
            }

            JwtAuthenticationToken authRequest = new JwtAuthenticationToken(token);

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException exception) {
            unsuccessfulAuthentication(request, response, exception);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, HttpServletResponse response,
                                            final FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        if(this.eventPublisher != null) {
            this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }

        chain.doFilter(request, response);
    }
}
