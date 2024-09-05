package com.jsl.shop_inn.common.util;

import com.jsl.shop_inn.models.Furniture;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseMessage {
    private String username;
    private String fullName;
    private String message;
    private List<Furniture> furniture;
    private BigDecimal totalSpent;
}
