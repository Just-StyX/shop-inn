package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.models.Furniture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface FurnitureRepository extends JpaRepository<Furniture, String> {
    Page<Furniture> findByName(String name, Pageable pageable);
    Page<Furniture> findByNameAndPrice(String name, BigDecimal price, Pageable pageable);
}
