package com.be.service;

import java.util.Optional;

import com.be.entities.Cart;


public interface CartService {
	public Optional<Cart> getCartById(String cartId);
}
