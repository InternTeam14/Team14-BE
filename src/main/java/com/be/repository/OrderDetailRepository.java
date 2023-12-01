package com.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.be.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT SUM(o.product) FROM OrderDetail o")
	public Long countTotatlProductSell();
	
	@Query("SELECT COUNT(o.amount) FROM OrderDetail o")
	public Long countTotatlAmountOfProduct();
	
	@Query("SELECT SUM(o.quantity) FROM OrderDetail o")
	public Long countTotatlQuantityOfProduct();
	
	@Query("SELECT o FROM OrderDetail o GROUP BY o.orders.orderId,o.product.productID")
	public List<OrderDetail> listProductSell();
}
