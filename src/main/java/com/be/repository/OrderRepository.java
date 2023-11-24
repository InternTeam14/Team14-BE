package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.be.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT COUNT(o)as sum FROM Order o")
	public Long countTotalOrder();
	@Query("SELECT COUNT(o.users)as sum FROM Order o")
	public Long countTotalUser();
	
	
	
}
