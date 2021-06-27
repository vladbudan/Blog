package com.budan.springappblog.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoShowComment {

    private Integer id;
    private String message;
    private String article;
    private Integer userId;
    private String createdAt;
}
