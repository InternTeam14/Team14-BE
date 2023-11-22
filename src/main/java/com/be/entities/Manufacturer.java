package com.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manuId;
    private String title;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
