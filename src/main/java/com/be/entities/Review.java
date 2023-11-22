package com.be.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reId;

    @Column(length = 10000)
    private String content;
    private Double value;

    private String image;
    private Date dateCreate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToOne
    private User user;
}
