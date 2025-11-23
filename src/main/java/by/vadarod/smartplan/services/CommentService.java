package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.Comment;

public interface CommentService {
    Comment addComment(Comment comment);
    Comment findCommentById(Long commentId);
    void deleteCommentById(Long commentId);
    Comment updateComment(Comment comment);
}
