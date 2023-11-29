package com.be.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable{
	
	private String cateId;
	
	
	
	private String title;
	private Boolean isEdit = false;
	
}
