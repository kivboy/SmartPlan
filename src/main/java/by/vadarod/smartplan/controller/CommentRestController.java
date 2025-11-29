package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;
import by.vadarod.smartplan.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
@Tag(name = "Комментарии", description = "Сервисы по работе с комментариями")
public class CommentRestController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Добавление комментария", description = "Добавление нового комментария")
    public CommentResponse addComment(@RequestBody CommentCreateRequest createRequest) {
        return commentService.addComment(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение комментария", description = "Получение комментария по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("id") Long commentId) {
        CommentResponse commentResponse = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponse,  HttpStatus.OK);
    }

    @PatchMapping("/update")
    @Operation(summary = "Обновление комментария", description = "Редактирование комментария")
    public CommentResponse updateComment(@RequestBody CommentUpdateRequest updateRequest) {
        return commentService.updateComment(updateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление комментария", description = "Удаление комментария по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long commentId) {
        commentService.deleteCommentById(commentId);
    }
}
