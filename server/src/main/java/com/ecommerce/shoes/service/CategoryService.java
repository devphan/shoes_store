package com.ecommerce.shoes.service;

import java.util.List;

import com.ecommerce.shoes.entity.Category;
import com.ecommerce.shoes.model.request.CategoryReq;

public interface CategoryService {
    List<Category> getListCategory();

    Category getDetailCategory(int id);

    Category createCategory(CategoryReq req);

    Category deleteCategory(int id);
}
