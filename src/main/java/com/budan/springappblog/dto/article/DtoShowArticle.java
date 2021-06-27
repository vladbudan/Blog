package com.budan.springappblog.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoShowArticle {
    private Integer id;
    private String title;
    private String text;
    private String status;
    private String author;
    private String createdAt;
    private String updatedAt;
}
