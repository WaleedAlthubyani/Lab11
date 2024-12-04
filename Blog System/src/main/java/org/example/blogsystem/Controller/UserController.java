package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiResponse;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/blog-system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse<>("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("User deleted successfully"));
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getUserById(id)));
    }

    @PostMapping("/login/username/{username}/password/{password}")
    public ResponseEntity<ApiResponse<String>> login(@PathVariable String username, @PathVariable String password){
        userService.login(username,password);
        return ResponseEntity.status(200).body(new ApiResponse<>("Login successful"));
    }

    @GetMapping("/get-users-after-date/{date}")
    public ResponseEntity<ApiResponse<List<User>>> getUsersAfterDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getUsersAfterDate(date)));
    }

    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getUserByEmail(email)));
    }

}
