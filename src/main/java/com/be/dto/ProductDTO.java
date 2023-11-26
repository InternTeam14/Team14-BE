package com.be.dto;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private String productID;

    private String title;

    private String description;
    private String config;
    private Double price;
    private Double discountPrice;
    private String images;
    private Boolean active;
    private Integer quantity;
   
    private String manuId;
	private String cartId;
	private String cateId;
	private MultipartFile imageFile;
	private Boolean isEdit= false;
}
