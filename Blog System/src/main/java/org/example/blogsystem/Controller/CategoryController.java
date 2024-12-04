package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.Category;
import org.example.blogsystem.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/blog-system/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.status(200).body(new ApiResponse<>(categoryService.getAllCategories()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse<>("Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body(new ApiResponse<>("Category updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Category deleted successfully"));
    }

    @GetMapping("/get-category-by-id/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(categoryService.getCategoryById(id)));
    }

    @GetMapping("/get-category-by-name/{name}")
    public ResponseEntity<ApiResponse<Category>> getCategoryByName(@PathVariable String name){
        return ResponseEntity.status(200).body(new ApiResponse<>(categoryService.getCategoryByName(name)));
    }
}
