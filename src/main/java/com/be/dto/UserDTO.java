package com.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Boolean isEdit = false;
	private String userId;
	private String address;
	private String email;
	private String avatar;
	private MultipartFile imageFile;
	private String fullName;
	private String phone;
	private Boolean sex;
	private Boolean enabled=true;
}
