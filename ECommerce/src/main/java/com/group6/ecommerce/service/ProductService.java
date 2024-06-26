package com.group6.ecommerce.service;

import com.group6.ecommerce.dao.ProductRepo;
import com.group6.ecommerce.entity.Product;
import com.group6.ecommerce.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Service method to get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Service method to get a product by its ID
    public Product getProductById(Integer id) {
        Optional<Product> maybeProduct = productRepo.findById(id);
        if (maybeProduct.isPresent()) {
            return maybeProduct.get();
        } else {
            throw new ServiceException("Product with id " + id + " not found");
        }
    }

    // Service method to add a new product
    public Product addNewProduct(Product product) {
        if(productRepo.existsById(product.getId())) {
            throw new ServiceException("Product with id " + product.getId() + " already exists");
        }
        return productRepo.save(product);
    }

    public void deleteProduct(Integer id) {
        if (!productRepo.existsById(id)) {
            throw new ServiceException("Product with id " + id + " not found");
        }
        productRepo.deleteById(id);
    }

    //@todo: instead of using a full product, insert just the stock amount. I kinda know how to do this but don't have the time to implement it right now.
    // Service method to delete a product
    public Product updateProductStock(Integer id, Product productDetails) {
        Optional<Product> maybeProduct = productRepo.findById(id);
        if(maybeProduct.isPresent()) {
            Product newProductEntity = maybeProduct.get();
            newProductEntity.setStock(productDetails.getStock());
            return productRepo.save(newProductEntity);
        } else {
            throw new ServiceException("Product with id " + id + " not found");
        }
    }

    // Service method to delete a product using using PathVariable for ease of use
    public Product decreaseProductStock(Integer id) {
        Product product = getProductById(id);
        if(product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
            return productRepo.save(product);
        } else {
            throw new ServiceException("Product is out of stock");
        }
    }

}