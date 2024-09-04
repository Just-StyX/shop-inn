package com.jsl.shop_inn.controller.consumer_controller;

import com.jsl.shop_inn.common.util.ItemRequest;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.consumer_service.services.ConsumerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/{item-id}")
    public ResponseEntity<ItemResponse> uploadImages(
            @Parameter() // this is needed
            @RequestPart("files")List<MultipartFile> files, Authentication authentication,
            @PathVariable(name = "item-id") String furnitureId
    ) {
        return ResponseEntity.ok().body(consumerService.uploadFile(files, authentication, furnitureId));
    }
}
