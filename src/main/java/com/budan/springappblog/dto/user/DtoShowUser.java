package com.budan.springappblog.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoShowUser {
    private Integer id;
    private String firstname;
    private String lastName;
    private String createdAt;
    private String role;
}
