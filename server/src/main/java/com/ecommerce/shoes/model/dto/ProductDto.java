package com.ecommerce.shoes.model.dto;

import java.io.IOException;
import java.util.ArrayList;

import com.ecommerce.shoes.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public ProductDto(String name, String description, float price, String image, String brand,
            Object categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.brand = brand;
        if (categories != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.categories = mapper.readValue((String) categories, new TypeReference<ArrayList<String>>(){});
            } catch (IOException e) {
                this.categories = null;
            }
        }
    }
}
