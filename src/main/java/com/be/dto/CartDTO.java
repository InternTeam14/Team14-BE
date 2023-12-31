package com.be.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
  private String productID;
//  private String customerID;
  private String name;
  private double price;
  private int quantity;
  private Boolean isEdit=false;
}
