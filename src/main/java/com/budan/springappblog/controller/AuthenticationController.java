package com.budan.springappblog.controller;

import com.budan.springappblog.dto.login.LoginRequestDto;
import com.budan.springappblog.dto.login.LoginResponseDto;
import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.service.AuthenticationService;
import com.budan.springappblog.service.UserService;
import com.budan.springappblog.service.dtoConverters.DtoUserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final DtoUserConverter dtoUserConverter;

    @PostMapping("/login")
    public LoginResponseDto authentication(@RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }

    @PostMapping("/forgot_pass}")
    public void forgotPassword(@RequestBody UserDto user) {
        dtoUserConverter.fromAddToModel(userService.forgotPassword(user));
    }

    @PostMapping("/reset/{code}")
    public void newPassword(@PathVariable String code, @RequestBody UserDto user) throws IOException {
        dtoUserConverter.fromAddToModel(userService.newPassword(code, user));
    }

}
