package com.be.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.be.entities.Manufacturer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public interface ManufacturerService {

	Page<Manufacturer> findByNameContaining(String name, String categoriesID);

	<S extends Manufacturer> List<S> findAll(Example<S> example, Sort sort);

	<S extends Manufacturer> List<S> findAll(Example<S> example);

	Manufacturer getReferenceById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Manufacturer> entities);

	void deleteAllById(Iterable<? extends String> ids);

	Manufacturer getById(String id);

	void delete(Manufacturer entity);

	Manufacturer getOne(String id);

	void deleteById(String id);

	void deleteAllInBatch();

	<S extends Manufacturer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Manufacturer> boolean exists(Example<S> example);

	void deleteAllInBatch(List<Manufacturer> entities);

	boolean existsById(String id);

	void deleteInBatch(Iterable<Manufacturer> entities);

	Optional<Manufacturer> findById(String  id);

	<S extends Manufacturer> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Manufacturer> List<S> saveAllAndFlush(Iterable<S> entities);

	Manufacturer saveAndFlush(Manufacturer entity);

	void flush();

	<S extends Manufacturer> List<S> saveAll(Iterable<S> entities);

	List<Manufacturer> findAllById(Iterable<String> ids);

	List<Manufacturer> findAll(Sort sort);

	Page<Manufacturer> findAll(Pageable pageable);

	List<Manufacturer> findAll();

	<S extends Manufacturer> Optional<S> findOne(Example<S> example);

	Manufacturer save(Manufacturer entity);

	Page<Manufacturer> findByNameContaining(String name, Pageable pageable);

	List<Manufacturer> findByNameContaining(String name);

}
