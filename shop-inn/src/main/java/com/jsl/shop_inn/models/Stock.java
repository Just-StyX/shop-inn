package com.jsl.shop_inn.models;

import com.jsl.shop_inn.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock extends BaseEntity {
    @NotNull
    @Column(nullable = false)
    private String name;

    @Access(AccessType.PROPERTY)
    private Boolean inStock;
    @Min(value = 0, message = "quantity cannot be less than zero")
    private int quantity;

    public boolean getInStock() {
        return this.getQuantity() > 0;
    }

    @OneToOne(mappedBy = "stock")
    private Furniture furniture;

    public void setInStock() {
        this.inStock = this.getQuantity() > 0;
    }
}
