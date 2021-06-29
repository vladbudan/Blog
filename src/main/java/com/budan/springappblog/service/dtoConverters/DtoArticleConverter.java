package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.article.DtoAddArticle;
import com.budan.springappblog.dto.article.DtoEditArticle;
import com.budan.springappblog.dto.article.DtoShowArticle;
import com.budan.springappblog.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoArticleConverter {


    public Article fromAddToModel(final DtoAddArticle dtoAddArticle) {
        Article article = new Article();

        dtoAddArticle.setTitle(article.getTitle());
        dtoAddArticle.setText(article.getText());
        dtoAddArticle.setStatus(article.getStatus());

        return article;
    }

    public Article mergeEditAndModel(final Article article, DtoEditArticle dtoEditArticle) {

        article.setStatus(dtoEditArticle.getStatus());
        article.setTitle(dtoEditArticle.getTitle());
        article.setText(dtoEditArticle.getText());
        article.setUpdatedAt(LocalDate.now());

        return article;
    }

    public DtoShowArticle fromModelToShow(final Article article) {
        DtoShowArticle dtoShowArticle = new DtoShowArticle();

        dtoShowArticle.setTitle(article.getTitle());
        dtoShowArticle.setText(article.getText());
        dtoShowArticle.setStatus(article.getStatus().name());

        return dtoShowArticle;
    }

    public List<DtoShowArticle> fromListModelToListShow(final List<Article> articleList) {
        List<DtoShowArticle> dtoShowArticleList = new ArrayList<>(articleList.size());

        for(Article article: articleList) {
            dtoShowArticleList.add(this.fromModelToShow(article));
        }

        return dtoShowArticleList;
    }
}
