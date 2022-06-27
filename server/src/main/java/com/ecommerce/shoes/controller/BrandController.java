package com.ecommerce.shoes.controller;

import java.util.List;

import javax.validation.Valid;
import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.request.BrandReq;
import com.ecommerce.shoes.service.BrandService;
import com.ecommerce.shoes.service.ProductService;
import com.ecommerce.shoes.util.ResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getListBrand() {
        List<Brand> result = brandService.getListBrand();
        return ResponseEntity.ok(new ResponseObject(200, "Get_list_brand", result));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createBrand(@Valid @RequestBody BrandReq req) {
        Brand result = brandService.createBrand(req);
        return ResponseEntity.ok(new ResponseObject(201, "Create_brand_success", result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBrand(@PathVariable int id, @Valid @RequestBody BrandReq req) {
        Brand result = brandService.updateBrand(id, req);
        return ResponseEntity.ok(new ResponseObject(200, "Update_brand_success", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBrand(@PathVariable int id) {
        Brand result = brandService.deleteBrand(id);
        return ResponseEntity.ok(new ResponseObject(200, "Delete_brand_success", result));
    }

    // get list product by brand
    @GetMapping("/{name}")
    public ResponseEntity<ResponseObject> getListProductByBrand(@PathVariable String name) {
        String brandName = name.replace("-", " ");
        Brand brand = brandService.getBrandByName(brandName);
        List<ProductDto> result = productService.getListProductByBrand(brand);
        return ResponseEntity.ok(new ResponseObject(200, "Get_list_category_by_brand", result));

    }

}
