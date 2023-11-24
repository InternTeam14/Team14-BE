package com.be.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
	private Long productID;
	private String title;
	private String description;
	private String config;
	private Double price;
	private Double discountPrice;
	private String images;
	private Boolean active = false;
	private Integer quantity;
	private Long cateId;
	private Long manuId;
	private String cartId;
	private Boolean isEdit = false;
	
}
