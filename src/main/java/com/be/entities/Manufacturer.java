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
@Table(name = "manufacturer")
public class Manufacturer {
	@Id
	@Column(length = 12)
    private String manuId;
	@Column(length = 100, columnDefinition = "nvarchar(100)")
    private String title;

	@OneToMany(mappedBy = "manufacturer")
    private List<Product> products;
}
