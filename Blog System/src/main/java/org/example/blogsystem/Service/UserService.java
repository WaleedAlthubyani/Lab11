package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User oldUser = userRepository.findUserByUserId(id);

        if (oldUser==null)
            throw new ApiException("User not found");

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserByUserId(id);

        if (user==null)
            throw new ApiException("User not found");

        userRepository.delete(user);
    }

    public User getUserById(Integer id){
        User user=userRepository.findUserByUserId(id);

        if (user==null)
            throw new ApiException("User not found");

        return user;
    }

    public void login(String username,String password){
        User user = userRepository.login(username,password);

        if (user==null)
            throw new ApiException("Wrong username or password");
    }

    public List<User> getUsersAfterDate(LocalDate date){
        List<User> users = userRepository.usersAfterDate(date);

        if (users.isEmpty())
            throw new ApiException("There are no users matching this requirement");

        return users;
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);

        if (user==null)
            throw new ApiException("User not found");

        return user;
    }

}
