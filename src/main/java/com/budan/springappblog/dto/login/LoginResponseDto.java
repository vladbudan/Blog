package com.budan.springappblog.dto.login;

import com.budan.springappblog.dto.status.RequestStatusDto;
import com.budan.springappblog.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private RequestStatusDto errorDto;
    private int userId;
    private String firstName;
    private String lastName;
    private String userRole;

    public LoginResponseDto(String token, RequestStatusDto errorDto, User user) {
        this.token = token;
        this.errorDto = errorDto;
        if(user != null) {
            this.userId = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.userRole = user.getRoles().toString();
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RequestStatusDto getErrorDto() {
        return errorDto;
    }

    public void setErrorDto(RequestStatusDto errorDto) {
        this.errorDto = errorDto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
