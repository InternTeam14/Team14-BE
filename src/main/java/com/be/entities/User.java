package com.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	@Id
	@Column(length = 12)
    private String userId;
	@Column(length = 50, columnDefinition = "nvarchar(100)")
    private String fullName;
    private String email;
    private String phone;
	@Column(columnDefinition = "nvarchar(100)")
    private String address;
    private String avatar;
    private Boolean enabled;
    private Boolean sex;


	@OneToMany(mappedBy = "users")
    private List<Order> orders;
	@OneToMany(mappedBy = "users")
    private List<Comment> comments;
	@OneToMany(mappedBy = "users")
    private List<Cart> carts;
    
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Account account;

	public User(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", avatar=" + avatar + ", enabled=" + enabled + ", sex=" + sex + ", orders="
				+ orders + ", comments=" + comments + ", carts=" + carts + ", account=" + account + "]";
	}
    
}
