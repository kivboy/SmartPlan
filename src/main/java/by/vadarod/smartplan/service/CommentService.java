package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;

import java.util.Collection;

public interface CommentService {
    CommentResponse addComment(Long taskId, CommentCreateRequest createRequest);
    CommentResponse getCommentById(Long commentId);
    CommentResponse deleteCommentById(Long commentId);
    CommentResponse updateComment(Long commentId, CommentUpdateRequest updateRequest);
    Collection<CommentResponse> findCommentsByTaskId(Long commentId);
}
