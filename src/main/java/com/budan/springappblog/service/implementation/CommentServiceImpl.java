package com.budan.springappblog.service.implementation;

import com.budan.springappblog.constants.Abbreviation;
import com.budan.springappblog.dto.comment.DtoAddComment;
import com.budan.springappblog.dto.comment.DtoEditComment;
import com.budan.springappblog.dto.comment.DtoShowComment;
import com.budan.springappblog.model.Comment;
import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.CommentRepository;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.service.CommentService;
import com.budan.springappblog.service.dtoConverters.DtoCommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final DtoCommentConverter dtoCommentConverter;

    @Override
    public void add(DtoAddComment dtoAddComment, Integer postId) {
        Optional<User> post = userRepository.findById(postId);

        this.exceptionOnNull(post);

        Comment comment = dtoCommentConverter.fromAddToModel(dtoAddComment, post.get());
        commentRepository.save(comment);
    }

    @Override
    public void update(DtoEditComment dtoEditComment) {
        Optional<Comment> comment = commentRepository.findById(dtoEditComment.getId());

        exceptionOnNull(comment);

        dtoCommentConverter.mergeEditAndModel(dtoEditComment, comment.get());
        commentRepository.save(comment.get());
    }

    @Override
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<DtoShowComment> getAllByPostId(Integer postId) {
        return dtoCommentConverter.fromListModelToListShow(commentRepository.findAllById(postId));
    }

    @Override
    public DtoShowComment getById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);

        exceptionOnNull(comment);

        return dtoCommentConverter.fromModelToShow(comment.get());
    }

    @Override
    public void exceptionOnNull(Optional<Comment> comment) throws {
        if(comment.isEmpty()) {
            throw new ResourceNotFoundException(Abbreviation.ERROR_THAT_OBJECT_NOT_EXIST);
        }
    }

    private void exceptionOnNUll(Object object) {
        if(object == null) {
            throw new ResourceNotFoundException(Abbreviation.ERROR_THAT_OBJECT_NOT_EXIST);
        }
    }
}
