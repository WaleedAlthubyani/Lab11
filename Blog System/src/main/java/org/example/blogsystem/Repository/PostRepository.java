package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostByPostId(Integer id);

    Post findPostByTitle(String title);

    @Query("select p from Post p where p.publishDate between ?1 and ?2")
    List<Post> getPostsInDatesRange(LocalDate minDate,LocalDate maxDate);

    @Query("select p from Post p where p.userId=?1")
    List<Post> getPostsByUser(Integer id);

    List<Post> findPostsByCategoryId(Integer id);
}
