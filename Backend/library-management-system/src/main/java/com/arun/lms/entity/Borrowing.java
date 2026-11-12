package com.arun.lms.entity;

import com.arun.lms.enums.BorrowingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "borrowings")
@Getter
@Setter
@NoArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member who borrowed the book
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Physical copy that was borrowed
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "copy_id", nullable = false)
    private BookCopy copy;

    // Librarian who issued the book
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issued_by", nullable = false)
    private User issuedBy;

    // Librarian who processed the return
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_by")
    private User receivedBy;

    @Column(name = "borrowed_at", nullable = false, updatable = false)
    private Instant borrowedAt;

    @Column(name = "due_at", nullable = false)
    private Instant dueAt;

    @Column(name = "returned_at")
    private Instant returnedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BorrowingStatus status = BorrowingStatus.ACTIVE;

    @PrePersist
    protected void onCreate() {
        if (borrowedAt == null) {
            borrowedAt = Instant.now();
        }

        if (status == null) {
            status = BorrowingStatus.ACTIVE;
        }
    }
}
