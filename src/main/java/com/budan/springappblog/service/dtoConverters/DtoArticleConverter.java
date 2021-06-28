package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.article.DtoAddArticle;
import com.budan.springappblog.dto.article.DtoEditArticle;
import com.budan.springappblog.dto.article.DtoShowArticle;
import com.budan.springappblog.model.Article;
import com.budan.springappblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoArticleConverter {

    private final UserRepository userRepository;

    public Article fromAddToModel(final DtoAddArticle dtoAddArticle) {
        Article article = new Article();

        dtoAddArticle.setTitle(article.getTitle());
        dtoAddArticle.setText(article.getText());
        dtoAddArticle.setStatus(article.getStatus());

        return article;
    }

    public Article mergeEditAndModel(final Article article, final DtoEditArticle dtoEditArticle) {

        article.setId(dtoEditArticle.getId());
        article.setTitle(dtoEditArticle.getTitle());
        article.setText(dtoEditArticle.getText());
        article.setStatus(dtoEditArticle.getStatus());

        return article;
    }

    public DtoShowArticle fromModelToShow(final Article article) {
        DtoShowArticle dtoShowArticle = new DtoShowArticle();

        dtoShowArticle.setId(article.getId());
        dtoShowArticle.setTitle(article.getTitle());
        dtoShowArticle.setText(article.getText());
        dtoShowArticle.setStatus(article.getStatus().name());
        dtoShowArticle.setAuthor(article.getAuthor().getFirstName());
        dtoShowArticle.setUpdatedAt(article.getUpdatedAt().toString());

        return dtoShowArticle;
    }

    public List<DtoShowArticle> fromListToListShow(final List<Article> articleList) {
        List<DtoShowArticle> dtoShowArticleList = new ArrayList<>(articleList.size());

        for(Article article: articleList) {
            dtoShowArticleList.add(this.fromModelToShow(article));
        }

        return dtoShowArticleList;
    }
}
