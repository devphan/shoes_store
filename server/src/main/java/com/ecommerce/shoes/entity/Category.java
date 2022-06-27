package com.ecommerce.shoes.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    // @ManyToMany(mappedBy = "categories")
    // private List<Product> products;

}
