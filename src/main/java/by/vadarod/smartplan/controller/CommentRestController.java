package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.comment.CommentCreateRequest;
import by.vadarod.smartplan.dto.comment.CommentResponse;
import by.vadarod.smartplan.dto.comment.CommentUpdateRequest;
import by.vadarod.smartplan.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentRestController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponse addComment(@RequestBody CommentCreateRequest createRequest) {
        return commentService.addComment(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("id") Long commentId) {
        CommentResponse commentResponse = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponse,  HttpStatus.OK);
    }

    @PatchMapping("/update")
    public CommentResponse updateComment(@RequestBody CommentUpdateRequest updateRequest) {
        return commentService.updateComment(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long commentId) {
        commentService.deleteCommentById(commentId);
    }
}
