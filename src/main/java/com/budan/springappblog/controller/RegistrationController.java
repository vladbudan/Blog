package com.budan.springappblog.controller;

import com.budan.springappblog.dto.status.DtoRequestStatus;
import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public DtoRequestStatus register(@RequestBody UserDto user) {
        return userService.registerUser(user);
    }

    @GetMapping("/activate/{code}")
    public void activate(@PathVariable String code) throws IOException {
        userService.activateUser(code);
    }

}
