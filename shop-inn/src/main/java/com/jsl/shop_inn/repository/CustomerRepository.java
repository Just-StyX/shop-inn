package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String username);
}
