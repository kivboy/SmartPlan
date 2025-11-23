package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentCreateRequest createRequest);
    //Comment toEntity(CommentUpdateRequest updateRequest);
    CommentResponse toResponse(Comment comment);
}
