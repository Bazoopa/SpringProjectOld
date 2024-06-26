package com.group6.ecommerce.service;

import com.group6.ecommerce.entity.Basket;
import com.group6.ecommerce.dao.BasketRepo;
import com.group6.ecommerce.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    // Auto-inject BasketRepo for access to CRUD operations on the basket table
    @Autowired
    private BasketRepo basketRepo;

    // Service method to get all baskets
    public List<Basket> getAllBaskets() {
        return basketRepo.findAll();
    }

    // Service method to get a basket by its ID
    public Basket getBasketById(long id) {
        Optional<Basket> maybeBasket = basketRepo.findById(id);
        if (maybeBasket.isPresent()) {
            return maybeBasket.get();
        } else {
            throw new ServiceException("Basket with id " + id + " not found");
        }
    }

    // Service method to create a new basket
    public Basket createNewBasket(Basket basket) {
        if (basketRepo.existsById(basket.getId())) {
            throw new ServiceException("Basket with id " + basket.getId() + " already exists");
        }
        return basketRepo.save(basket);
    }

    // @todo - test and fix the update Basket method
    // Service method to update the quantity of an item in a basket, probably needs more work!
    public Basket updateBasket(Long id, Basket quantityDetails) {
        Optional<Basket> maybeBasket = basketRepo.findById(id);
        if (maybeBasket.isPresent()) {
            Basket entity = maybeBasket.get();
            entity.setQuantity(quantityDetails.getQuantity());
            return basketRepo.save(entity);
        } else {
            throw new ServiceException("Basket with id " + id + " not found");
        }
    }

    // Service method to delete a basket
    public void deleteBasket(long id) {
        if (!basketRepo.existsById(id)) {
            throw new ServiceException("Basket with id " + id + " not found");
        }
        basketRepo.deleteById(id);
    }


}