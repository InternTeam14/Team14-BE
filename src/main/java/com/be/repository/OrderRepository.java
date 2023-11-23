package com.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.be.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT COUNT(o) FROM Order o")
	public Long countTotalOrder();
	@Query("SELECT SUM(o.username) FROM Order o")
	public Long countTotalUserByPrduct();
	@Query("SELECT SUM(o.quantity) FROM Order o")
	public Long totalQuantitySell();
	@Query("SELECT SUM(o.orderAmount) FROM Order o")
	public Long totalOrderAmountSell();
	
	@Query("SELECT o FROM Order o GROUP BY o.pId")
	public List<Order> listOrder();
	
}
