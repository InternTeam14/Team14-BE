package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
	@Column(length = 12)
    private String orderId;
	@Column(columnDefinition = "nvarchar(1000)")
    private String note;
	@Column(length = 50, columnDefinition = "nvarchar(100)")
    private String orderStatus;
    private Double totalAmount;
    private Integer totalQuantity;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        orderDate = calendar.getTime();
    }

	@OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private User users;
}
