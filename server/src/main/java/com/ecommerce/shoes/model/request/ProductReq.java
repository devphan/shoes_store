package com.ecommerce.shoes.model.request;

import java.util.ArrayList;

import javax.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReq {
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;

    @NotNull
    private float price;

    @NotNull
    @NotEmpty
    private String description;

    private String image;

    private int brandId;

    private ArrayList<Integer> categoryIds;
}
