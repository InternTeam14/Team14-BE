package com.be.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.be.entities.Cart;
import com.be.repository.CartRepository;
import com.be.service.CartService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
	private final CartRepository cartRepository;

	@Override
	public Optional<Cart> getCartById(String cartId) {
		return cartRepository.findById(cartId);
	}

}
