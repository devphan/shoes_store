package com.ecommerce.shoes.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String name;

    private String thumbnail;

    // @OneToMany(mappedBy = "brand")
    // private List<Product> products;
}
