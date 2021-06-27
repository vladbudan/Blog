package com.budan.springappblog.service;

import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.model.User;

public interface UserService {
    User findByFirstname(String firstName);
    User registerUser(UserDto userDto);
    void activateUser(String code);
    void deleteUser(Integer id);
    UserDto forgotPassword(UserDto userDto);
    UserDto newPassword(String code, UserDto userDto);
    boolean uniqueUsername(String username);
    boolean userIsEnabled(String username);
}
