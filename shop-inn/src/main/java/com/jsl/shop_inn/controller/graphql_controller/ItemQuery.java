package com.jsl.shop_inn.controller.graphql_controller;

import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.common.util.PageResponse;
import com.jsl.shop_inn.service.repository.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class ItemQuery {
    private final FurnitureService furnitureService;

    @QueryMapping
    Mono<PageResponse<ItemResponse>> queryByName(@Argument String name, @Argument int page, @Argument int size) {
        return Mono.just(furnitureService.findByName(name, page, size));
    }

    @QueryMapping
    Mono<PageResponse<ItemResponse>> queryByNameAndPrice(@Argument String name, @Argument BigDecimal price, @Argument int page, @Argument int size) {
        return Mono.just(furnitureService.findByNameAndPrice(name, price, page, size));
    }

}
