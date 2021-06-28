package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoUserConverter {

    public User fromAddToModel(final UserDto userDto) {
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
