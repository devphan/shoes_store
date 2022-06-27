package com.ecommerce.shoes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.request.ProductReq;
import com.ecommerce.shoes.service.ProductService;
import com.ecommerce.shoes.util.ResponseObject;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getListProduct(@RequestParam(required = false) String name) {
        List<ProductDto> result = null;
        if (name == null)
            result = productService.getListProduct();
        else
            result = productService.searchProduct(name);

        return ResponseEntity.ok(new ResponseObject(200, "Get_list_product", result));
    }

    @GetMapping("/paging/{pageIndex}/{pageSize}")
    public ResponseEntity<ResponseObject> getPageProduct(@RequestParam(required = false) String name,
            @PathVariable int pageIndex, @PathVariable int pageSize) {
        List<ProductDto> result = null;
        if (name == null)
            result = productService.getPageProduct(pageIndex, pageSize);
        else
            result = productService.searchProduct(name);

        return ResponseEntity.ok(new ResponseObject(200, "Get_list_product", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDetailProduct(@PathVariable Long id) {
        ProductDto result = productService.getDetailProduct(id);
        return ResponseEntity.ok(new ResponseObject(200, "Get_detail_product", result));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createProduct(@Valid @RequestBody ProductReq req) {
        ProductDto result = productService.createProduct(req);
        return ResponseEntity.ok(new ResponseObject(201, "Create_product_success", result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductReq req) {
        ProductDto result = productService.updateProduct(id, req);
        return ResponseEntity.ok(new ResponseObject(200, "Update_product_success", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseObject(200, "Delete_product_success", "OK"));
    }

}
