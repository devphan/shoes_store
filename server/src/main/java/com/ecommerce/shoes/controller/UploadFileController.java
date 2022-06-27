package com.ecommerce.shoes.controller;

import java.io.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.shoes.exception.BadRequestException;
import com.ecommerce.shoes.exception.InternalServerException;
import com.ecommerce.shoes.util.ResponseObject;
import com.ecommerce.shoes.util.UploadForm;

@RestController
public class UploadFileController {
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/upload-java";

    @PostMapping("/upload-image")
    public ResponseEntity<ResponseObject> uploadImage(@ModelAttribute UploadForm form) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists())
            uploadDir.mkdirs();

        MultipartFile fileData = form.getFileData();
        String name = fileData.getOriginalFilename();
        if (name == null && name.length() < 0)
            throw new BadRequestException("Bad_request");

        String extension = name.substring(name.lastIndexOf("."));
        String[] extensionImgList = { ".png", ".jpg" };
        boolean extensionValid = Arrays.asList(extensionImgList).contains(extension);
        if (!extensionValid)
            throw new BadRequestException("Extension_invalid");

        try {
            String now = LocalDateTime.now().toString().replace(":", "-").replace(".", "-");
            File serverFile = new File(UPLOAD_DIR + "/" + now + "_" + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(fileData.getBytes());
            stream.close();
            return ResponseEntity.ok(new ResponseObject(200, "Upload_success", serverFile));
        } catch (Exception e) {
            throw new InternalServerException("Error_when_uploading");
        }
    }
}
