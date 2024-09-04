package com.jsl.shop_inn.models;

import com.jsl.shop_inn.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "furniture")
public class Furniture extends BaseEntity {
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    @Positive(message = "price of furniture must be greater than zero(0)")
    private BigDecimal price;
    @Access(AccessType.PROPERTY)
    private Boolean inStock;
    @Embedded
    private Description description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "furniture", cascade = CascadeType.ALL)
    private List<FurnitureImageNames> furnitureImageNames = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock")
    private Stock stock;


    public void setInStock() {
        this.inStock = this.stock.getInStock();
    }
}
