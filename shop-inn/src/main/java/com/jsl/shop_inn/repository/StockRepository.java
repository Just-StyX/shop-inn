package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
