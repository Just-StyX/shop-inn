package com.jsl.shop_inn.common.util;

import lombok.Getter;

@Getter
public enum FurnitureCategory {
    BED("bed"), TABLE("table"), COUCH("couch");

    private final String name;

    FurnitureCategory(String name) {
        this.name = name;
    }
}
