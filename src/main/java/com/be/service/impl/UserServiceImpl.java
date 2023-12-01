package com.be.service.impl;

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

import com.be.entities.Account;
import com.be.entities.User;
import com.be.repository.UserRepository;
import com.be.service.UserService;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void updateEnabled(Boolean enabled, String userId) {
		userRepository.updateEnabled(enabled, userId);
	}

	@Override
	public List<User> findByFullnameContaining(String name) {
		return userRepository.findByfullNameContaining(name);
	}

	@Override
	public User findByAccount_Username(String username) {
		return userRepository.findByAccount_Username(username);
	}

	@Override
	public Page<User> findByFullnameContaining(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		return userRepository.findOne(example);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}

	@Override
	public List<User> findAllById(Iterable<String> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public <S extends User> List<S> saveAll(Iterable<S> entities) {
		return userRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		userRepository.flush();
	}

	@Override
	public User saveAndFlush(User entity) {
		return userRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userRepository.findAll(example, pageable);
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<User> entities) {
		userRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<User> entities) {
		userRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		return userRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		userRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		userRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getOne(String id) {
		return userRepository.getOne(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public User getById(String id) {
		return userRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		userRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public User getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		return userRepository.findAll(example);
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		return userRepository.findAll(example, sort);
	}

	@Override
	public Optional<User> findUserByAccount(Account account) {
		Optional<User> user = userRepository.findUserByAccount(account);
		return user;
	}

}
