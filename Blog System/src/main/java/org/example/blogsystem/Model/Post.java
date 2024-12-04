package org.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotEmpty(message = "Please enter a title")
    @Column(columnDefinition = "varchar(255) not null")
    private String title;

    @NotEmpty(message = "Please enter post's content")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDate publishDate;

    @NotNull(message = "Please enter category Id")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotNull(message = "Please enter user Id")
    @Column(columnDefinition = "int not null")
    private Integer userId;
}
