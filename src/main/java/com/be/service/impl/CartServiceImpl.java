package com.be.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.be.entities.Cart;
import com.be.repository.CartRepository;
import com.be.service.CartService;

import lombok.RequiredArgsConstructor;

@Service

@Repository
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartReponsitory;

	@Override
	public Cart save(Cart entity) {
		return cartReponsitory.save(entity);
	}

	@Override
	public <S extends Cart> Optional<S> findOne(Example<S> example) {
		return cartReponsitory.findOne(example);
	}

	@Override
	public List<Cart> findAll() {
		return cartReponsitory.findAll();
	}

	@Override
	public Page<Cart> findAll(Pageable pageable) {
		return cartReponsitory.findAll(pageable);
	}

	@Override
	public List<Cart> findAll(Sort sort) {
		return cartReponsitory.findAll(sort);
	}

	@Override
	public List<Cart> findAllById(Iterable<String> ids) {
		return cartReponsitory.findAllById(ids);
	}

	@Override
	public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
		return cartReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		cartReponsitory.flush();
	}

	@Override
	public Cart saveAndFlush(Cart entity) {
		return cartReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<Cart> findById(String id) {
		return cartReponsitory.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Cart> entities) {
		cartReponsitory.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return cartReponsitory.existsById(id);
	}

	@Override
	public <S extends Cart> boolean exists(Example<S> example) {
		return cartReponsitory.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		cartReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Cart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cartReponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		cartReponsitory.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		cartReponsitory.deleteById(id);
	}

	@Override
	public Cart getOne(String id) {
		return cartReponsitory.getOne(id);
	}

	@Override
	public void delete(Cart entity) {
		cartReponsitory.delete(entity);
	}

	@Override
	public Cart getById(String id) {
		return cartReponsitory.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		cartReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Cart> entities) {
		cartReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		cartReponsitory.deleteAll();
	}

	@Override
	public Cart getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Cart> List<S> findAll(Example<S> example) {
		return cartReponsitory.findAll(example);
	}

	@Override
	public <S extends Cart> List<S> findAll(Example<S> example, Sort sort) {
		return cartReponsitory.findAll(example, sort);
	}

	@Override
	public void deleteAllInBatch(List<Cart> entities) {
		cartReponsitory.deleteInBatch(entities);

	}

	@Override
	public void deleteByUserID(String userID) {
		cartReponsitory.deleteByUsersUserId(userID);

	}

	@Override
	public List<Cart> findByUserID(String userID) {
		return cartReponsitory.findByUsersUserId(userID);
	}

	@Override
	public Long countByUserID(String userId) {
		return cartReponsitory.countByUserId(userId);
	}

}
