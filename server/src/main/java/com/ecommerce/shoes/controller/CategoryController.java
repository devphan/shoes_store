package com.ecommerce.shoes.controller;

import com.ecommerce.shoes.entity.Category;

import com.ecommerce.shoes.model.request.CategoryReq;
import com.ecommerce.shoes.service.CategoryService;
import com.ecommerce.shoes.service.ProductService;
import com.ecommerce.shoes.util.ResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getListCategory() {
        List<Category> result = categoryService.getListCategory();
        return ResponseEntity.ok(new ResponseObject(200, "Get_list_category", result));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createCategory(@Valid @RequestBody CategoryReq req) {
        Category result = categoryService.createCategory(req);
        return ResponseEntity.ok(new ResponseObject(201, "Create_category_success", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteCategory(@PathVariable int id) {
        Category result = categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ResponseObject(200, "Delete_category_success", result));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseObject> getListProductByCategory(@PathVariable String name) {
        String categoryName = name.replace("-", " ");
        return ResponseEntity.ok(new ResponseObject(200, "Get_list_product_by_category",
                productService.getListProductByCategory(categoryName)));

    }

}
