package com.jsl.shop_inn.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ItemResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private DescriptionResponse descriptionResponse;
    private List<byte[]> files;
}