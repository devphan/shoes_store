package com.ecommerce.shoes.model.request;

import javax.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandReq {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;

    private String thumbnail;
}
