package com.ecommerce.shoes.util;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {
    private int status;
    private String message;
    private Object data;
}
