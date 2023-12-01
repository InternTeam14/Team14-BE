package com.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.be.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT COUNT(o)as sum FROM Order o")
	public Long countTotalOrder();
	@Query("SELECT COUNT(o.users)as sum FROM Order o")
	public Long countTotalUser();
	
	@Query("SELECT o FROM Order o WHERE o.users.userId=?1")
	List<Order> findByUsername(String username);
	

	@Query("SELECT o FROM Order o WHERE o.orderId=?1")
	Order findByOrderId(Long orderId);
	
	
	
	
}
