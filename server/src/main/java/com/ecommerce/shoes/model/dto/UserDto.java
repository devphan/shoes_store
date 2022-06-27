package com.ecommerce.shoes.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    private String fullName;

    private String email;

    private String phone;

    private String avatar;

    private String address;

    private String role;

}
