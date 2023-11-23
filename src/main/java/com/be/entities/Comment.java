package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comId;
	@Column(columnDefinition = "nvarchar(1000)")
    private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cmtDate;

	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        cmtDate = calendar.getTime();
    }

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private User users;
	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
}
