package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.user.DtoShowUser;
import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public DtoShowUser fromModelToShow(final User user) {
        DtoShowUser dtoShowUser = new DtoShowUser();

        dtoShowUser.setId(user.getId());
        dtoShowUser.setFirstname(user.getFirstName());
        dtoShowUser.setLastName(user.getLastName());
        dtoShowUser.setCreatedAt(user.getCreatedAt().toString());
        dtoShowUser.setRole(user.getRoles().toString());

        return dtoShowUser;
    }

    public List<DtoShowUser> fromListModelToListShow(final List<User> users) {
        List<DtoShowUser> dtoShowUsers = new ArrayList<>(users.size());

        for(User user: users) {
            dtoShowUsers.add(this.fromModelToShow(user));
        }

        return dtoShowUsers;
    }

}
