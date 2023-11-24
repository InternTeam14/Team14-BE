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

import com.be.entities.Manufacturer;
import com.be.repository.ManufacturerRepository;
import com.be.service.ManufacturerService;

@Repository
public class ManufacturerServiceImpl implements ManufacturerService {
	@Autowired
	private ManufacturerRepository manufacturerRepository;


	@Override
	public Page<Manufacturer> findByNameContaining(String name, Pageable pageable) {
		return manufacturerRepository.findByTitleContaining(name, pageable);
	}

	@Override
	public List<Manufacturer> findByNameContaining(String name) {
		return manufacturerRepository.findByTitleContaining(name);
	}

	@Override
	public Manufacturer save(Manufacturer entity) {
		return manufacturerRepository.save(entity);
	}

	@Override
	public <S extends Manufacturer> Optional<S> findOne(Example<S> example) {
		return manufacturerRepository.findOne(example);
	}

	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public Page<Manufacturer> findAll(Pageable pageable) {
		return manufacturerRepository.findAll(pageable);
	}

	@Override
	public List<Manufacturer> findAll(Sort sort) {
		return manufacturerRepository.findAll(sort);
	}

	@Override
	public List<Manufacturer> findAllById(Iterable<String> ids) {
		return manufacturerRepository.findAllById(ids);
	}

	@Override
	public <S extends Manufacturer> List<S> saveAll(Iterable<S> entities) {
		return manufacturerRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		manufacturerRepository.flush();
	}

	@Override
	public Manufacturer saveAndFlush(Manufacturer entity) {
		return manufacturerRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Manufacturer> List<S> saveAllAndFlush(Iterable<S> entities) {
		return manufacturerRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Manufacturer> Page<S> findAll(Example<S> example, Pageable pageable) {
		return manufacturerRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Manufacturer> findById(String id) {
		return manufacturerRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Manufacturer> entities) {
		manufacturerRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return manufacturerRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<Manufacturer> entities) {
		manufacturerRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Manufacturer> boolean exists(Example<S> example) {
		return manufacturerRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		manufacturerRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Manufacturer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return manufacturerRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		manufacturerRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		manufacturerRepository.deleteById(id);
	}

	@Override
	public Manufacturer getOne(String id) {
		return manufacturerRepository.getOne(id);
	}

	@Override
	public void delete(Manufacturer entity) {
		manufacturerRepository.delete(entity);
	}

	@Override
	public Manufacturer getById(String id) {
		return manufacturerRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		manufacturerRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Manufacturer> entities) {
		manufacturerRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		manufacturerRepository.deleteAll();
	}

	@Override
	public Manufacturer getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Manufacturer> List<S> findAll(Example<S> example) {
		return manufacturerRepository.findAll(example);
	}

	@Override
	public <S extends Manufacturer> List<S> findAll(Example<S> example, Sort sort) {
		return manufacturerRepository.findAll(example, sort);
	}

	@Override
	public Page<Manufacturer> findByNameContaining(String name, String categoriesID) {
		// TODO Auto-generated method stub
		return null;
	}


}
