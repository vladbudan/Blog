package com.budan.springappblog.service.implementation;

import com.budan.springappblog.constants.Abbreviation;
import com.budan.springappblog.dto.login.LoginRequestDto;
import com.budan.springappblog.dto.login.LoginResponseDto;
import com.budan.springappblog.dto.status.RequestStatusDto;
import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.security.model.JwtUserDetails;
import com.budan.springappblog.security.service.AuthenticationHelper;
import com.budan.springappblog.service.AuthenticationService;
import com.budan.springappblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.json.JsonException;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String username = ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException(Abbreviation.BAD_CREDENTIALS_USER_PASSED));
            String password = ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException(Abbreviation.BAD_CREDENTIALS_PASS_PASSED));
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);
            if (userService.uniqueUsername(username)) {
                return new LoginResponseDto(null, new RequestStatusDto(Abbreviation.ERROR, Abbreviation.ERROR_UNIQUE_USERNAME),
                        null);
            }
            if (!this.userService.userIsEnabled(loginRequestDto.getUsername())) {
                return new LoginResponseDto(null, new RequestStatusDto(Abbreviation.ERROR, Abbreviation.ERROR_USER_NOT_ENABLED),
                        null);
            }
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                User user = userRepository.findById(userDetails.getId()).get();
                String token = this.authenticationHelper.generateToken(userDetails.getId());
                return new LoginResponseDto(token,
                        new RequestStatusDto(Abbreviation.SUCCESS, Abbreviation.SUCCESS_AUTHENTICATION),
                        user);
            } else {
                throw new JsonException(Abbreviation.AUTH_FILED);
            }
        } catch (BadCredentialsException exception) {
            return new LoginResponseDto(null, new RequestStatusDto(Abbreviation.ERROR, Abbreviation.ERROR_INVALID_PARAMETRS),
                    null);
        }
    }
}
