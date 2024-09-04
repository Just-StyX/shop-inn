package com.jsl.shop_inn.controller.consumer_controller;

import com.jsl.shop_inn.common.util.ItemRequest;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.consumer_service.services.ConsumerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
@Tag(name = "Item")
public class ConsumerController {
    private final ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<ItemResponse> addItem(
            @RequestBody @Valid ItemRequest itemRequest, Authentication authentication
            ) {
        return ResponseEntity.ok().body(consumerService.addItem(itemRequest, authentication));
    }
}
