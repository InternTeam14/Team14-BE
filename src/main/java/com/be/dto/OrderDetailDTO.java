package com.be.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO implements Serializable {
	private Long orderDtId;
    private Integer quantity;
    private Double amount;
    private Long productID;
    private String orderId;
    private Boolean isEdit = false;
}
