package com.arun.lms.entity;

import com.arun.lms.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(name="member_number",nullable = false,unique = true,length = 30)
    private String memberNumber;

    @Column(length = 20)
    private String phone;

    @Column(name = "joined_at",nullable = false,updatable = false)
    private LocalDate joinedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private MemberStatus status;

    @PrePersist
    protected void onCreate(){
        if(joinedAt==null){
            joinedAt=LocalDate.now();
        }
        if(status ==null){
            status=MemberStatus.ACTIVE;
        }
    }

}
