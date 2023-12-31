package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderDetail")
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDtId;
    private Integer quantity;
    private Double amount;

	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "orderId", referencedColumnName = "orderId")
	private Order orders;
}
