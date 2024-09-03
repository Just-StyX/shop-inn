package com.jsl.shop_inn.common.base;

import com.jsl.shop_inn.models.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String token;
    private LocalDateTime createdDate;
    private LocalDateTime expiredAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
