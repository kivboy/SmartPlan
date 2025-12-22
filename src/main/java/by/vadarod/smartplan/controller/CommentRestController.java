package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;
import by.vadarod.smartplan.exception.model.ErrorResponse;
import by.vadarod.smartplan.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
@Tag(name = "Комментарии", description = "Сервисы по работе с комментариями")
public class CommentRestController {
    private final CommentService commentService;

    @PostMapping("/task/{taskId}")
    @Operation(summary = "Добавление комментария", description = "Добавление нового комментария")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = CommentResponse.class)))
    public CommentResponse addComment(@PathVariable("taskId") Long taskId, @RequestBody @Validated CommentCreateRequest createRequest) {
        return commentService.addComment(taskId, createRequest);
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Получение комментариев", description = "Получение комментариев по id задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список комментариев к задаче",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CommentResponse.class))
                    )
            )
    })
    public Collection<CommentResponse> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.findCommentsByTaskId(taskId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение комментария", description = "Получение комментария по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("id") Long commentId) {
        CommentResponse commentResponse = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponse,  HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление комментария", description = "Редактирование комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public CommentResponse updateComment(@PathVariable("id") Long commentId, @RequestBody @Validated CommentUpdateRequest updateRequest) {
        return commentService.updateComment(commentId, updateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление комментария", description = "Удаление комментария по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public CommentResponse delete(@PathVariable("id") Long commentId) {
        return commentService.deleteCommentById(commentId);
    }
}
