package com.be.dto;

import java.io.Serializable;


import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDTO implements Serializable{
	private String manuId;
	private String title;
	private Boolean isEdit = false;

}
