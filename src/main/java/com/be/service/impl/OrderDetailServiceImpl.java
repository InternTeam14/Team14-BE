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
import org.springframework.stereotype.Service;

import com.be.entities.Order;
import com.be.entities.OrderDetail;
import com.be.repository.OrderDetailsRepository;
import com.be.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailsRepository detailsRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return detailsRepository.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return detailsRepository.findOne(example);
	}

	@Override
	public List<OrderDetail> findAll() {
		return detailsRepository.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return detailsRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return detailsRepository.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Long> ids) {
		return detailsRepository.findAllById(ids);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return detailsRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		detailsRepository.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return detailsRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return detailsRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return detailsRepository.findAll(example, pageable);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return detailsRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		detailsRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return detailsRepository.existsById(id);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return detailsRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		detailsRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return detailsRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		detailsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return detailsRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return detailsRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		detailsRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		detailsRepository.deleteById(id);
	}

	@Override
	public OrderDetail getOne(Long id) {
		return detailsRepository.getOne(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		detailsRepository.delete(entity);
	}

	@Override
	public OrderDetail getById(Long id) {
		return detailsRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		detailsRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		detailsRepository.deleteAll(entities);
	}

	@Override
	public OrderDetail getReferenceById(Long id) {
		return detailsRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		detailsRepository.deleteAll();
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return detailsRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return detailsRepository.findAll(example, sort);
	}

	
	
}
