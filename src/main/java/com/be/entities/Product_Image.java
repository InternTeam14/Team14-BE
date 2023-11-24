package com.be.entities;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class Product_Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Column(length = 50000000)
    private byte[] pcyByte;


	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
}
