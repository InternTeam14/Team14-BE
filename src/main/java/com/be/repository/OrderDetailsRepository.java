package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.be.entities.Order;
import com.be.entities.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {

}
