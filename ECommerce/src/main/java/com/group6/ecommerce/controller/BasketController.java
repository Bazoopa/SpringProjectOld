package com.group6.ecommerce.controller;

import com.group6.ecommerce.entity.Basket;
import com.group6.ecommerce.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    @Autowired
    private BasketService basketService;

    // Endpoint to get all baskets
    @GetMapping("/all")
    public ResponseEntity<List<Basket>> getAllBaskets() {
        List<Basket> basketEntities = basketService.getAllBaskets();
        return ResponseEntity.status(HttpStatus.OK).body(basketEntities);
    }

    // Endpoint to get a basket by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable Integer id) {
        Basket basket = basketService.getBasketById(id);
        return ResponseEntity.ok(basket);
    }

    // Endpoint to add a new basket
    @PostMapping("/add")
    public ResponseEntity<Void> createNewBasket(@RequestBody Basket basket) {
        basketService.createNewBasket(basket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to update a basket - ToDo
    // @todo Need this to only update the basket, could be quite challenging. will do later.
    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable long id, @RequestBody Basket basketEntityDetails) {
        Basket basket = basketService.updateBasket(id, basketEntityDetails);
        return ResponseEntity.ok(basket);
    }

    // Endpoint to delete a basket using its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Integer id) {
        basketService.deleteBasket(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}