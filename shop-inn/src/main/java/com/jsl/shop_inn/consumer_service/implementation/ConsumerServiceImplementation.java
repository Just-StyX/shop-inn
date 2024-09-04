package com.jsl.shop_inn.consumer_service.implementation;

import com.jsl.shop_inn.common.util.FurnitureCategory;
import com.jsl.shop_inn.common.util.ItemRequest;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.consumer_service.services.ConsumerService;
import com.jsl.shop_inn.models.Customer;
import com.jsl.shop_inn.models.Description;
import com.jsl.shop_inn.models.Furniture;
import com.jsl.shop_inn.models.Stock;
import com.jsl.shop_inn.repository.FurnitureRepository;
import com.jsl.shop_inn.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.jsl.shop_inn.common.mappers.ModelMappers.toItemResponse;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImplementation implements ConsumerService {
    private final StockRepository stockRepository;
    private final FurnitureRepository furnitureRepository;

    @Override
    public ItemResponse addItem(ItemRequest itemRequest, Authentication authentication) {
        Customer customer = (Customer) authentication.getPrincipal();
        var value = FurnitureCategory.BED;
        var category = itemRequest.category().toLowerCase();
        if (category.equals("table")) value = FurnitureCategory.TABLE;
        if (category.equals("couch")) value = FurnitureCategory.COUCH;
        Description description = new Description(
                itemRequest.color(), itemRequest.dimension(), value, itemRequest.additional()
        );

        Stock stock = stockRepository.save(Stock.builder().name(itemRequest.name()).quantity(itemRequest.quantity()).build());

        Furniture furniture = Furniture.builder()
//                .id(stock.getId())
                .name(itemRequest.name())
                .price(itemRequest.price())
                .inStock(stock.getInStock())
                .description(description)
                .stock(stock)
                .build();
        return toItemResponse(furnitureRepository.save(furniture));
    }
}
