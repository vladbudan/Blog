package com.budan.springappblog.repository;

import com.budan.springappblog.model.Article;
import com.budan.springappblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllById(Integer id);
}
