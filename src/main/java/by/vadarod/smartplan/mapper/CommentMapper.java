package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CommentMapper {
    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    Comment toEntity(CommentCreateRequest createRequest);
    CommentResponse toResponse(Comment comment);
}
