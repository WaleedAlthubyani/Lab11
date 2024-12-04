package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentByCommentId(Integer id);

    List<Comment> findCommentsByPostId(Integer id);

    List<Comment> findCommentsByUserId(Integer id);

    List<Comment> findCommentsByContentContainingIgnoreCase(String text);
}
