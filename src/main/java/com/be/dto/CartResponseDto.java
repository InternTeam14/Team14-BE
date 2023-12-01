package com.be.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDto {
	private String cartId;
	
	private int quantity;
	
	private ProductDTO productDTO;
	
	private Double price;

	private Float currentPrice;
	
	private Double total;
	
}
