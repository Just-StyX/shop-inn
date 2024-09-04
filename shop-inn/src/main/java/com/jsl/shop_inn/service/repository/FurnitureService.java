package com.jsl.shop_inn.service.repository;

import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.common.util.PageResponse;

import java.math.BigDecimal;

public interface FurnitureService {
    PageResponse<ItemResponse> findByName(String name, int page, int size);
    PageResponse<ItemResponse> findByNameAndPrice(String name, BigDecimal price, int page, int size);
}
