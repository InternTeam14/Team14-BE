package com.be.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModelDTO implements Serializable {

	private Long orderId;
	private String note;
	private String orderStatus;
	private Double totalAmount;
	private Integer totalQuantity;
	private Date orderDate;
	private String userId;
	private Boolean isEdit = false;

}
