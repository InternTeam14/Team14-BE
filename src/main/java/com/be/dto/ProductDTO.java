package com.be.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.beans.propertyeditors.InputSourceEditor;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private String productID;
    private String title;
    private String description;
    private String config;
    private Double price;
    private Double discountPrice;
    private String images;
    
    private Integer quantity;
    private String cateId;
    private String manuId;
    private Boolean active = true;
    private Boolean isEdit =  true;
    private MultipartFile imgFile;
    
    
}
