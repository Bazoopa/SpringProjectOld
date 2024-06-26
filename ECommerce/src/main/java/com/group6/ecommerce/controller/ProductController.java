package com.group6.ecommerce.controller;

import com.group6.ecommerce.entity.Product;
import com.group6.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Endpoint to get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Endpoint to add a new product
    @PostMapping("/add")
    public ResponseEntity<Void> addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to delete a product by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to update a product's stock
    // @todo add a Put command to update the product stock!
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        Product product = productService.updateProductStock(id, updatedProduct);
        return ResponseEntity.ok(product);
    }

    // Endpoint to update a product's stock - using PathVariable instead for ease of use
    @PutMapping("/{id}/decreaseStock")
    public ResponseEntity<Product> decreaseProductStock(@PathVariable Integer id) {
        Product product = productService.decreaseProductStock(id);
        return ResponseEntity.ok(product);
    }

}