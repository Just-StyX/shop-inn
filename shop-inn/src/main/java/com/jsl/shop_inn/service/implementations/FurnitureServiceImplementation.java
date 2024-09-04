package com.jsl.shop_inn.service.implementations;

import com.jsl.shop_inn.common.mappers.ModelMappers;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.common.util.PageResponse;
import com.jsl.shop_inn.models.Furniture;
import com.jsl.shop_inn.repository.FurnitureRepository;
import com.jsl.shop_inn.service.repository.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FurnitureServiceImplementation implements FurnitureService {
    private final FurnitureRepository furnitureRepository;

    @Override
    public PageResponse<ItemResponse> findByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        Page<Furniture> furniture = furnitureRepository.findByName(name, pageable);
        List<ItemResponse> itemResponses = furniture.stream().map(ModelMappers::toItemResponse).toList();
        return new PageResponse<>(
                itemResponses, furniture.getNumber(), furniture.getSize(), furniture.getTotalElements(),
                furniture.getTotalPages(), furniture.isFirst(), furniture.isLast()
        );
    }

    @Override
    public PageResponse<ItemResponse> findByNameAndPrice(String name, BigDecimal price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        Page<Furniture> furniture = furnitureRepository.findByNameAndPrice(name, price, pageable);
        List<ItemResponse> itemResponses = furniture.stream().map(ModelMappers::toItemResponse).toList();
        return new PageResponse<>(
                itemResponses, furniture.getNumber(), furniture.getSize(), furniture.getTotalElements(),
                furniture.getTotalPages(), furniture.isFirst(), furniture.isLast()
        );
    }
}
