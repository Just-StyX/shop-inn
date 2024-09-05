package com.jsl.shop_inn.consumer_service.implementation;

import com.jsl.shop_inn.common.util.*;
import com.jsl.shop_inn.consumer_service.services.ConsumerService;
import com.jsl.shop_inn.exception.CustomerNotFoundException;
import com.jsl.shop_inn.file.FileStorageService;
import com.jsl.shop_inn.models.*;
import com.jsl.shop_inn.repository.CustomerRepository;
import com.jsl.shop_inn.repository.FurnitureRepository;
import com.jsl.shop_inn.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.jsl.shop_inn.common.mappers.ModelMappers.toItemResponse;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImplementation implements ConsumerService {
    private final StockRepository stockRepository;
    private final FurnitureRepository furnitureRepository;
    private final FileStorageService fileStorageService;
    private final CustomerRepository customerRepository;


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
                .name(itemRequest.name())
                .price(itemRequest.price())
                .inStock(stock.getInStock())
                .description(description)
                .stock(stock)
                .build();
        return toItemResponse(furnitureRepository.save(furniture));
    }

    @Override
    public ItemResponse uploadFile(List<MultipartFile> files, Authentication authentication, String furnitureId) {
        Customer customer = (Customer) authentication.getPrincipal();
        Furniture furniture = furnitureRepository.findById(furnitureId).orElseThrow(() -> new CustomerNotFoundException("Item does not exists"));

        for (MultipartFile file: files) {
            String filename = fileStorageService.saveFile(file, furnitureId);
            FurnitureImageNames furnitureImageNames = new FurnitureImageNames(filename, furniture);
            furnitureRepository.save(furniture.addImage(furnitureImageNames));
        }

        return toItemResponse(furnitureRepository.findById(furnitureId).orElseThrow(() -> new CustomerNotFoundException("Item does not exists")));
    }

    @Override
    public PurchaseMessage processing(PurchaseRequest purchaseRequest, Authentication authentication) {
        List<Furniture> furniture = furnitureRepository.findAllById(purchaseRequest.furnitureIds());
        Address billing = new Address(purchaseRequest.state(), purchaseRequest.city(), purchaseRequest.street(), purchaseRequest.zipcode());
        Address shipping = new Address(purchaseRequest.shipping_state(), purchaseRequest.shipping_city(), purchaseRequest.shipping_street(), purchaseRequest.shipping_zipcode());
        Customer customer = (Customer) authentication.getPrincipal();
        customer.setBillingAddress(billing);
        customer.setShipping(shipping);
        Cart cart = Cart.builder()
                .furniture(furniture)
                .build();
        cart.setAmountSpent(cart.amountSpent());
        Customer savedCustomer = customerRepository.save(customer.addCart(cart));
        return PurchaseMessage.builder()
                            .username(customer.getEmail())
                            .fullName(customer.getFullName())
                            .message("The following items will be shipped to the shipping address provided")
                            .furniture(furniture)
                            .totalSpent(cart.amountSpent())
                            .build();
    }
}
