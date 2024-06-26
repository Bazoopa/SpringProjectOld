package com.group6.ecommerce.dao;

import com.group6.ecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepo extends JpaRepository<Basket, Long> {
}
