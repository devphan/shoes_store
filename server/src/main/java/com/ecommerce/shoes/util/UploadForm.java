package com.ecommerce.shoes.util;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadForm {
    private MultipartFile fileData;
}
