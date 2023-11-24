package com.be.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

	@Column(columnDefinition = "nvarchar(100)")
    private String content;
    private Double value;

    private String image;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        createDate = calendar.getTime();
    }

	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User users;
}
