package com.arun.lms.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 150)
    private String author;

    @Column(length = 150)
    private String publisher;

    @Column(length = 100)
    private String category;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookCopy> copies = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}