package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.Post;
import org.example.blogsystem.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/blog-system/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getAllPosts()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        postService.addPost(post);
        return ResponseEntity.status(201).body(new ApiResponse<>("Post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse<>("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Post deleted successfully"));
    }

    @GetMapping("/get-post-by-id/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostById(id)));
    }

    @GetMapping("/get-post-by-title/{title}")
    public ResponseEntity<ApiResponse<Post>> getPostByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostByTitle(title)));
    }

    @GetMapping("/get-posts-in-date-range/first-date/{date1}/second-date/{date2}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostsInDateRange(@PathVariable LocalDate date1,@PathVariable LocalDate date2){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsInDateRange(date1,date2)));
    }

    @GetMapping("/get-posts-by-user-id/{id}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsByUser(id)));
    }

    @GetMapping("/get-post-by-category-id/{id}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostByCategoryId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsByCategory(id)));
    }
}
