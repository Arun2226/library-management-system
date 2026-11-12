package com.arun.lms.entity;


import com.arun.lms.enums.BookCopyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_copies")
@Getter
@Setter
@NoArgsConstructor
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(
            name = "accession_number",
            nullable = false,
            unique = true,
            length = 50
    )
    private String accessionNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookCopyStatus status;

    @Column(name = "acquired_at")
    private LocalDate acquiredAt;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = BookCopyStatus.AVAILABLE;
        }
    }
}