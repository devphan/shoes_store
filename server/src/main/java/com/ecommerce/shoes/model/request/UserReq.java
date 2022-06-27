package com.ecommerce.shoes.model.request;

import javax.validation.constraints.*;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReq {

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;


}
