package com.be.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable{
	@Id
	@Column(length = 20)
	private String username;
	@Column(length = 20)
	private String password;
	private String role;
	
	@OneToOne(mappedBy = "account")
    private User users;

	public Account(String username) {
		this.username = username;
	}
	
	
}
