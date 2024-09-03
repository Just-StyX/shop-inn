package com.jsl.shop_inn.models;

import com.jsl.shop_inn.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart extends BaseEntity {
    @Access(AccessType.PROPERTY)
    private BigDecimal amountSpent;
    private LocalDateTime purchasedDate;
    private LocalDateTime deliveredDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Furniture> furniture = new ArrayList<>();

    public void setAmountSpent() {
        this.amountSpent = this.furniture.stream().map(Furniture::getPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }
}
