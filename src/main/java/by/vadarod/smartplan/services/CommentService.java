package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;

public interface CommentService {
    CommentResponse addComment(CommentCreateRequest createRequest);
    CommentResponse getCommentById(Long commentId);
    void deleteCommentById(Long commentId);
    CommentResponse updateComment(CommentUpdateRequest updateRequest);
}
