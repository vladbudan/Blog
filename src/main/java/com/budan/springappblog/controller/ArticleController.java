package com.budan.springappblog.controller;

import com.budan.springappblog.dto.article.DtoAddArticle;
import com.budan.springappblog.dto.article.DtoEditArticle;
import com.budan.springappblog.dto.article.DtoShowArticle;
import com.budan.springappblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("get/user/{user_id}")
    public List<DtoShowArticle> getUserArticles(@PathVariable("user_id") Integer postId) {
        return articleService.getAllByUserId(postId);
    }

    @GetMapping("/get/{article_id}")
    public DtoShowArticle getArticle(@PathVariable("article_id") Integer articleId) {
        return articleService.getById(articleId);
    }

    @GetMapping("/get/my")
    @PreAuthorize("hasAuthority('ARTICLE')")
    public List<DtoShowArticle> getArticleByCurrentUser() {
        return articleService.getAllByCurrentUser();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ARTICLE')")
    public void addArticle(@RequestBody DtoAddArticle dtoAddArticle) {
        articleService.add(dtoAddArticle);
    }

    @PutMapping("/edit/")
    @PreAuthorize("hasAuthority('ARTICLE')")
    public void editArticle(@RequestBody DtoEditArticle dtoEditArticle) {
        articleService.update(dtoEditArticle);
    }

    @DeleteMapping("/delete/{article_id}")
    @PreAuthorize("hasAuthority('ARTICLE')")
    public void deleteArticle(@PathVariable("article_id") Integer articleId) {
        articleService.delete(articleId);
    }

}
