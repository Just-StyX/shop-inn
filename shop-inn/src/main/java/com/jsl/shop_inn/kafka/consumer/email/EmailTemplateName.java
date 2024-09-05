package com.jsl.shop_inn.kafka.consumer.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    PROCESSING("processing");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
