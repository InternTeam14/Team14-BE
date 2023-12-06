package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(length = 12)
	private String productID;
	@Column(length = 100, columnDefinition = "nvarchar(100)")
	private String title;
	@Column(columnDefinition = "nvarchar(1500)")
	private String description;
	private String config;
	private Double price;
	private Double discountPrice;
	private String images;
	private Boolean active;
	private Integer quantity;

	@OneToMany(mappedBy = "product")
	private List<Product_Image> product_Images;
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;
	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
	@OneToMany(mappedBy = "product")
	private List<Cart> carts;

	@ManyToOne
	@JoinColumn(name = "manuId", referencedColumnName = "manuId")
	private Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "cateId", referencedColumnName = "cateId")
	private Category category;

	public Product(String productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", title=" + title + ", description=" + description + ", config="
				+ config + ", price=" + price + ", discountPrice=" + discountPrice + ", images=" + images + ", active="
				+ active + ", quantity=" + quantity + ", product_Images=" + product_Images + ", orderDetails="
				+ orderDetails + ", reviews=" + reviews + ", comments=" + comments + ", carts=" + carts
				+ ", manufacturer=" + manufacturer + ", category=" + category + "]";
	}

}
