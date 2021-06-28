package com.budan.springappblog.service;

import com.budan.springappblog.dto.login.LoginRequestDto;
import com.budan.springappblog.dto.login.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto login(final LoginRequestDto loginRequestDto);
}
