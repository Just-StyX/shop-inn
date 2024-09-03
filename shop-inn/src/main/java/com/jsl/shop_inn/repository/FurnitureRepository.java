package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.models.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, String> {
}
