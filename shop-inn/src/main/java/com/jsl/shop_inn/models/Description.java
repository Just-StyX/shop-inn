package com.jsl.shop_inn.models;

import com.jsl.shop_inn.common.util.FurnitureCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Description {
    @NotNull
    @Column(nullable = false)
    private String color;
    @NotNull
    @Column(nullable = false)
    private String dimensions;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FurnitureCategory category;
    @NotNull
    @Column(nullable = false)
    private String additional;
}
