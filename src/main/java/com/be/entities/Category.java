package com.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "category")
public class Category {
	@Id
	@Column(length = 12)
	private String cateId;
	@Column(length = 100, columnDefinition = "nvarchar(100)")
    private String title;


	@OneToMany(mappedBy = "category")
    private List<Product> products;
}

