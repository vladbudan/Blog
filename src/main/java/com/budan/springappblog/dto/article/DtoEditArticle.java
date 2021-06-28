package com.budan.springappblog.dto.article;

import com.budan.springappblog.model.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoEditArticle {
    private Integer id;
    private String title;
    private String text;
    private ArticleStatus status;
}
