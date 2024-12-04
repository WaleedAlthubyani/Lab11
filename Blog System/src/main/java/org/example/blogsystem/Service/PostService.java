package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Post;
import org.example.blogsystem.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        userService.getUserById(post.getUserId());

        categoryService.getCategoryById(post.getCategoryId());

        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post){
        Post oldPost = postRepository.findPostByPostId(id);


        if (oldPost==null)
            throw new ApiException("post not found");

        userService.getUserById(post.getUserId());
        categoryService.getCategoryById(post.getCategoryId());

        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUserId(post.getUserId());
        oldPost.setCategoryId(post.getCategoryId());

        postRepository.save(oldPost);
    }

    public void deletePost(Integer id){
        Post post = postRepository.findPostByPostId(id);

        if (post==null)
            throw new ApiException("Post not found");

        postRepository.delete(post);
    }

    public Post getPostById(Integer id){
        Post post=postRepository.findPostByPostId(id);

        if (post==null)
            throw new ApiException("post not found");

        return post;
    }

    public Post getPostByTitle(String title){
        Post post=postRepository.findPostByTitle(title);

        if (post==null)
            throw new ApiException("Title not found");

        return post;
    }

    public List<Post> getPostsInDateRange(LocalDate minDate,LocalDate maxDate){
        List<Post> posts = postRepository.getPostsInDatesRange(minDate,maxDate);

        if (posts.isEmpty())
            throw new ApiException("No posts match your query");

        return posts;
    }

    public List<Post> getPostsByUser(Integer id){
        List<Post> posts = postRepository.getPostsByUser(id);

        if (posts.isEmpty())
            throw new ApiException("No posts found matching user id");

        return posts;
    }

    public List<Post> getPostsByCategory(Integer id){
        List<Post> posts = postRepository.findPostsByCategoryId(id);

        if (posts.isEmpty())
            throw new ApiException("No posts found matching category id");

        return posts;
    }
}
