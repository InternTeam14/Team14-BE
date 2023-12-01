package com.be.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OderDTO {
 private String orderId;
 private String note;
 private String orderStatus;
 private Double totalAmount;
 private Integer totalQuantity;
 private Date orderDate;
 private String userId;
}
