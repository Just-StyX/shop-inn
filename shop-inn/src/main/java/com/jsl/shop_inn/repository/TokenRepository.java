package com.jsl.shop_inn.repository;

import com.jsl.shop_inn.common.base.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByToken(String token);
}
