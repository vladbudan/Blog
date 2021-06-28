package com.budan.springappblog.service;

import com.budan.springappblog.dto.article.DtoAddArticle;
import com.budan.springappblog.dto.article.DtoEditArticle;
import com.budan.springappblog.dto.article.DtoShowArticle;

import java.util.List;

public interface ArticleService {
    void add(DtoAddArticle dtoAddArticle);
    void update(DtoEditArticle dtoEditArticle);
    void delete(Integer id);
    List<DtoShowArticle> getAllByUserId(Integer id);
    DtoShowArticle getById(Integer id);
    List<DtoShowArticle> getAllByCurrentUser();
}
