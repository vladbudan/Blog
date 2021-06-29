package com.budan.springappblog.dto.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoRequestStatus {
    private String message;
    private String status;

    public DtoRequestStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
