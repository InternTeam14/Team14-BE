package com.be.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comId;
	@Column(columnDefinition = "nvarchar(1000)")
    private String content;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cmtDate;

	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        cmtDate = calendar.getTime();
    }
	
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private User users;
	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;
}
