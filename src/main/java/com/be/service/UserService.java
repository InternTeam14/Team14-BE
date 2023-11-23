package com.be.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.be.entities.User;

public interface UserService {

	List<User> findByFullnameContaining(String name);

	void updateEnabled(Boolean enabled, String userId);

	User findByAccount_Username(String username);

	Page<User> findByFullnameContaining(String name, Pageable pageable);

	User save(User entity);

	<S extends User> Optional<S> findOne(Example<S> example);

	List<User> findAll();

	Page<User> findAll(Pageable pageable);

	List<User> findAll(Sort sort);

	List<User> findAllById(Iterable<String> ids);

	<S extends User> List<S> saveAll(Iterable<S> entities);

	void flush();

	User saveAndFlush(User entity);

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<User> findById(String id);

	void deleteInBatch(Iterable<User> entities);

	boolean existsById(String id);

	void deleteAllInBatch(List<User> entities);

	<S extends User> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch();

	void deleteById(String id);

	User getOne(String id);

	void delete(User entity);

	User getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends User> entities);

	void deleteAll();

	User getReferenceById(String id);

	<S extends User> List<S> findAll(Example<S> example);

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

}
