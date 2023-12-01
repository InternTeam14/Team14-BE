package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.be.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{

}
