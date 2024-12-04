package org.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "Please enter a username")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String username;

    @NotEmpty(message = "Please enter a password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least one digit [0-9].Password must contain at least one lowercase Latin character [a-z].Password must contain at least one uppercase Latin character [A-Z].Password must contain at least one special character like ! @ # & ( ).Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDate registrationDate;
}
