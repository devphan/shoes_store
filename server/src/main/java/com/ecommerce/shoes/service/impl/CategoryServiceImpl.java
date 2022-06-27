package com.ecommerce.shoes.service.impl;

import java.util.List;
import java.util.Optional;

import com.ecommerce.shoes.entity.Category;
import com.ecommerce.shoes.exception.DuplicateRecordException;
import com.ecommerce.shoes.exception.InternalServerException;
import com.ecommerce.shoes.exception.NotFoundException;
import com.ecommerce.shoes.model.request.CategoryReq;
import com.ecommerce.shoes.repository.CategoryRepository;
import com.ecommerce.shoes.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getDetailCategory(int id) {
        try {
            return categoryRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException("Category_does_not_exist");
        }
    }

    @Override
    public Category createCategory(CategoryReq req) {
        Category find = categoryRepository.findByName(req.getName());
        if (find != null)
            throw new DuplicateRecordException("Category_already_exist");
        Category category = new Category();
        category.setName(req.getName());
        try {
            categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            throw new InternalServerException("Create_category_failed");
        }
    }

    @Override
    public Category deleteCategory(int id) {
        Optional<Category> find = categoryRepository.findById(id);
        if (find.isEmpty())
            throw new NotFoundException("Category_does_not_exist");
        try {
            categoryRepository.deleteById(id);
            return find.get();
        } catch (Exception e) {
            throw new InternalServerException("Delete_category_failed");
        }
    }

}
