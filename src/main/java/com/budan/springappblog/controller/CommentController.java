package com.budan.springappblog.controller;

import com.budan.springappblog.dto.comment.DtoAddComment;
import com.budan.springappblog.dto.comment.DtoEditComment;
import com.budan.springappblog.dto.comment.DtoShowComment;
import com.budan.springappblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get/user/{post_id}")
    public List<DtoShowComment> getByPost(@PathVariable("post_id") Integer postId) {
        return commentService.getAllByPostId(postId);
    }

    @GetMapping("/get/{comment_id}")
    public DtoShowComment getComment(@PathVariable("comment_id") Integer commentId) {
        return commentService.getById(commentId);
    }

    @PostMapping("/add/{post_id}")
    public void addComment(@PathVariable("post_id") Integer postId, @RequestBody DtoAddComment dtoAddComment) {
        commentService.add(dtoAddComment, postId);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('USER')")
    public void updateComment(@RequestBody DtoEditComment dtoEditComment) {
        commentService.update(dtoEditComment);
    }

    @PostMapping("/delete/{comment_id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteComment(@PathVariable("comment_id") Integer commentId) {
        commentService.delete(commentId);
    }

}
