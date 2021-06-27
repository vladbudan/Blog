package com.budan.springappblog.dto.article;

import com.budan.springappblog.model.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddArticle {
    private String title;
    private String text;
    private ArticleStatus status;
}
