package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart implements Serializable {
	@Id
	@Column(length = 12)
	private String cartId;
	private Integer quantity;
	private Double totalAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date savedDate;
	
	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        savedDate = calendar.getTime();
    }
	

	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private User users;

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", savedDate="
				+ savedDate + ", product=" + product + ", users=" + users + "]";
	}
}
