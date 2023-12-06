package com.be.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

	@Size(min = 1, max = 70,message="Họ và tên không được để trống!")
	private String fullName;
	
	@Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "Số điện thoại không được để trống và từ 10 - 12 số!!")
	private String phone;
	
	private String address;

	private String note;
	
	List<String> cartIds;
}
