package com.budan.springappblog.service;

import com.budan.springappblog.dto.login.DtoLoginRequest;
import com.budan.springappblog.dto.login.DtoLoginResponse;

public interface AuthenticationService {
    DtoLoginResponse login(final DtoLoginRequest dtoLoginRequest);
}
