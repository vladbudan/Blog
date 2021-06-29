package com.budan.springappblog.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
