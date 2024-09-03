package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
