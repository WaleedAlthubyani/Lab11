package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.Comment;
import org.example.blogsystem.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/blog-system/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Comment>>> getAllComments() {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getAllComments()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse<>("Comment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse<>("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Comment deleted successfully"));
    }

    @GetMapping("/get-comment-by-id/{id}")
    public ResponseEntity<ApiResponse<Comment>> getCommentById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentById(id)));
    }

    @GetMapping("/get-comments-by-post-id/{id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentByPostId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getPostComments(id)));
    }

    @GetMapping("/get-comments-by-user-id/{id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getUserComments(id)));
    }

    @GetMapping("/get-comments-containing-text/{text}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentContainingText(@PathVariable String text){
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentsContainingText(text)));
    }
}
