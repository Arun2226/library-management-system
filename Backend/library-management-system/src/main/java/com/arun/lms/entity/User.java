package com.arun.lms.entity;

import com.arun.lms.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name",nullable=false,length =150)
    private String fullName;

    @Column(name="email",nullable = false,length = 255)
    private String email;

    @Column(name="password_hash",nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private UserRole role;

    @Column(name = "is_active",nullable = false)
    private boolean active = true;

    @Column(name="created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }


}
