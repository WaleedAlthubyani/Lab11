package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Comment;
import org.example.blogsystem.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        userService.getUserById(comment.getUserId());
        postService.getPostById(comment.getPostId());

        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment){
        Comment oldComment = commentRepository.findCommentByCommentId(id);

        if (oldComment==null)
            throw new ApiException("Comment not found");

        userService.getUserById(comment.getUserId());
        postService.getPostById(comment.getPostId());

        oldComment.setContent(comment.getContent());
        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());

        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentByCommentId(id);

        if (comment==null)
            throw new ApiException("Comment not found");

        commentRepository.delete(comment);
    }

    public Comment getCommentById(Integer id){
        Comment comment = commentRepository.findCommentByCommentId(id);

        if (comment==null)
            throw new ApiException("Comment not found");

        return comment;
    }

    public List<Comment> getPostComments(Integer id){
        List<Comment> comments = commentRepository.findCommentsByPostId(id);

        if (comments.isEmpty())
            throw new ApiException("Post's comments not found");

        return comments;
    }

    public List<Comment> getUserComments(Integer id){
        List<Comment> comments = commentRepository.findCommentsByUserId(id);

        if (comments.isEmpty())
            throw new ApiException("User's comments not found");

        return comments;
    }

    public List<Comment> getCommentsContainingText(String text){
        List<Comment> comments = commentRepository.findCommentsByContentContainingIgnoreCase(text);

        if (comments.isEmpty())
            throw new ApiException("No comments matches your inquiry");

        return comments;
    }
}
