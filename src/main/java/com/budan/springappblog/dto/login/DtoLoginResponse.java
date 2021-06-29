package com.budan.springappblog.dto.login;

import com.budan.springappblog.dto.status.DtoRequestStatus;
import com.budan.springappblog.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoLoginResponse {

    private String token;
    private DtoRequestStatus errorDto;
    private int userId;
    private String firstName;
    private String lastName;
    private String userRole;

    public DtoLoginResponse(String token, DtoRequestStatus errorDto, User user) {
        this.token = token;
        this.errorDto = errorDto;
        if(user != null) {
            this.userId = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.userRole = user.getRoles().toString();
        }
    }

}
