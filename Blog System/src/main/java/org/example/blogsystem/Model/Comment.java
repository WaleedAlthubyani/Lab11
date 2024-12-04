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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotEmpty(message = "Please enter comment's content")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDate commentDate;

    @NotNull(message = "Please enter user Id")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Please enter post id")
    @Column(columnDefinition = "int not null")
    private Integer postId;
}
