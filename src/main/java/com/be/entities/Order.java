package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderFullName;
    private String orderFullOder;
    private String orderContactNumber;
    private String note;
    private String orderStatus;
    private Double orderAmount;
    private Integer quantity;
    private Date orderDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
