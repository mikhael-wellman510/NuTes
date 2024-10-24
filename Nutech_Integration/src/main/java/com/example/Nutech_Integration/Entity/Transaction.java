package com.example.Nutech_Integration.Entity;

import com.example.Nutech_Integration.Entity.Auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_transaction")
@Builder(toBuilder = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", createdOn=" + createdOn +
                '}';
    }
}
