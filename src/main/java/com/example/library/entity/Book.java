package com.example.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Title is required")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "Author is required")
    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "published_date")
    private LocalDate publishedDate;
}
