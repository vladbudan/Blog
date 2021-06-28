package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.comment.DtoAddComment;
import com.budan.springappblog.dto.comment.DtoEditComment;
import com.budan.springappblog.dto.comment.DtoShowComment;
import com.budan.springappblog.model.Comment;
import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoCommentConverter {

    private final UserRepository userRepository;

    public Comment fromAddToModel(final DtoAddComment dtoAddComment, User user) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = new Comment();

        comment.setAuthor(author);
        comment.setMessage(dtoAddComment.getMessage());
        comment.setArticle(user);

        return comment;
    }

    public Comment mergeEditAndModel(final DtoEditComment dtoAddComment, final Comment comment) {
        comment.setId(dtoAddComment.getId());
        comment.setMessage(dtoAddComment.getMessage());

        return comment;
    }

    public DtoShowComment fromModelToShow(final Comment comment) {
        DtoShowComment dtoShowComment = new DtoShowComment();

        dtoShowComment.setUserId(comment.getAuthor().getId());
        dtoShowComment.setCreatedAt(comment.getCreatedAt().toString());
        dtoShowComment.setMessage(comment.getMessage());

        return dtoShowComment;
    }

    public List<DtoShowComment> fromListModelToListShow(final List<Comment> commentList) {
        List<DtoShowComment> dtoShowCommentList = new ArrayList<>(commentList.size());

        for(Comment comment: commentList) {
            dtoShowCommentList.add(this.fromModelToShow(comment));
        }

        return dtoShowCommentList;
    }



}
