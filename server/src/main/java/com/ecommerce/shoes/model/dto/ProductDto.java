package com.ecommerce.shoes.model.dto;

import java.util.ArrayList;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private float price;

    private String image;

    private String brand;

    private ArrayList<String> categories;
    
}
