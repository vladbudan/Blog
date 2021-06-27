package com.budan.springappblog.dto.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestStatusDto {
    private String message;
    private String status;

    public RequestStatusDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
