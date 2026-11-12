package com.arun.lms.entity;

import com.arun.lms.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member who requested the book
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Book being reserved
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status = ReservationStatus.WAITING;

    @Column(name = "requested_at", nullable = false, updatable = false)
    private Instant requestedAt;

    @Column(name = "fulfilled_at")
    private Instant fulfilledAt;

    @PrePersist
    protected void onCreate() {
        if (requestedAt == null) {
            requestedAt = Instant.now();
        }

        if (status == null) {
            status = ReservationStatus.WAITING;
        }
    }
}
