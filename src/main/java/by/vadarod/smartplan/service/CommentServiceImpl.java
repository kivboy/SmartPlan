package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;
import by.vadarod.smartplan.entity.Comment;
import by.vadarod.smartplan.entity.Task;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.mapper.CommentMapper;
import by.vadarod.smartplan.repository.CommentRepository;
import by.vadarod.smartplan.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse addComment(Long taskId, CommentCreateRequest createRequest) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Comment comment = commentMapper.toEntity(createRequest);
            comment.setTask(optionalTask.get());
            return commentMapper.toResponse(commentRepository.save(comment));
        } else {
            throw new EntityNotFoundException("Не найдена задача по id=" + taskId);
        }
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            return commentMapper.toResponse(commentOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден комментарий по id=" + commentId);
        }
    }

    @Override
    public CommentResponse deleteCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(commentId);
            return commentMapper.toResponse(optionalComment.get());
        } else {
            throw new EntityNotFoundException("Не найден комментарий по id=" + commentId);
        }

    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentUpdateRequest updateRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(updateRequest.getText());
            comment.setUpdated(LocalDateTime.now());
            return commentMapper.toResponse(commentRepository.save(comment));
        } else {
            throw new EntityNotFoundException("Не найден комментарий по id=" + commentId);
        }
    }

    @Override
    public Collection<CommentResponse> findCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
