package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;
import by.vadarod.smartplan.entity.Comment;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.CommentMapper;
import by.vadarod.smartplan.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse addComment(CommentCreateRequest createRequest) {
        Comment comment = commentMapper.toEntity(createRequest);
        return commentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            return commentMapper.toResponse(commentOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден comment по id=" + commentId);
        }
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponse updateComment(CommentUpdateRequest updateRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(updateRequest.getId());
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(updateRequest.getText());
            comment.setUpdated(LocalDateTime.now());
            return commentMapper.toResponse(commentRepository.save(comment));
        } else {
            throw new EntityNotFoundException("Не найден comment по id=" + updateRequest.getId());
        }
    }
}
