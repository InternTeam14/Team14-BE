package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.be.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, String>{
}
