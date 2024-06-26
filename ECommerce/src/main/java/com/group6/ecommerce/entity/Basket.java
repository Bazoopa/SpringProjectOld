package com.group6.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Basket")
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Defines the primary key of the "Basket" table. For some reason this has to be long instead of an int. do not change!
    private long id;

    // Creates a ManyToOne relationship between Basket and Customer.
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    //toDo: This needs to be a list of products. Not the end of the world if not possible, just have a basket of 1 product, can still set a quanitity of products.
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}