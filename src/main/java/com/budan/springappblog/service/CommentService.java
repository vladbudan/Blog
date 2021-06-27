package com.budan.springappblog.service;

import com.budan.springappblog.dto.comment.DtoAddComment;
import com.budan.springappblog.dto.comment.DtoEditComment;
import com.budan.springappblog.dto.comment.DtoShowComment;
import com.budan.springappblog.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void add(DtoAddComment dtoAddComment, Integer postId);
    void update(DtoEditComment dtoEditComment);
    void delete(Integer id);
    List<DtoShowComment> getAllByPostId(Integer postId);
    DtoShowComment getById(Integer id);
    void exceptionOnNull(Optional<Comment> comment);
}
