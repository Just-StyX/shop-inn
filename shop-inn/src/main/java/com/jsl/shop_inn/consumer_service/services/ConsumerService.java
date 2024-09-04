package com.jsl.shop_inn.consumer_service.services;

import com.jsl.shop_inn.common.util.ItemRequest;
import com.jsl.shop_inn.common.util.ItemResponse;
import org.springframework.security.core.Authentication;

public interface ConsumerService {
    ItemResponse addItem(ItemRequest itemRequest, Authentication authentication);
}
