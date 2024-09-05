package com.jsl.shop_inn.consumer_service.services;

import com.jsl.shop_inn.common.util.ItemRequest;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.common.util.PurchaseMessage;
import com.jsl.shop_inn.common.util.PurchaseRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsumerService {
    ItemResponse addItem(ItemRequest itemRequest, Authentication authentication);
    ItemResponse uploadFile(List<MultipartFile> files, Authentication authentication, String furnitureId);
    PurchaseMessage processing(PurchaseRequest purchaseRequest, Authentication authentication);
}
