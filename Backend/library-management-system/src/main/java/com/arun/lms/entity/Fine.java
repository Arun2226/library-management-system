
package com.arun.lms.entity;

import com.arun.lms.enums.FineStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "fines")
@Getter
@Setter
@NoArgsConstructor
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Borrowing record associated with this fine
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "borrowing_id",
            nullable = false,
            unique = true
    )
    private Borrowing borrowing;

    @Column(
            name = "amount",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FineStatus status = FineStatus.UNPAID;

    @Column(name = "paid_at")
    private Instant paidAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }

        if (status == null) {
            status = FineStatus.UNPAID;
        }
    }
}
