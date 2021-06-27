package com.budan.springappblog.repository;

import com.budan.springappblog.model.Article;
import com.budan.springappblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findByArticleId(Integer id);

    List<Article> findAllByUser(User user);
}
