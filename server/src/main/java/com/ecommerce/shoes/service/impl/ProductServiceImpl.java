package com.ecommerce.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.entity.Product;
import com.ecommerce.shoes.exception.InternalServerException;
import com.ecommerce.shoes.exception.NotFoundException;
import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.mapper.ProductMapper;
import com.ecommerce.shoes.model.request.ProductReq;
import com.ecommerce.shoes.repository.ProductRepository;
import com.ecommerce.shoes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDto> getPageProduct(int pageIndex, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageIndex, pageSize));
        return productMapper.toProductDtoList(productPage.toList());
    }

    @Override
    public List<ProductDto> getListProduct() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getListProductByBrand(Brand brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getListProductByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategory(categoryName);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public ProductDto getDetailProduct(Long id) {
        try {
            Product product = productRepository.findById(id).get();
            return productMapper.toProductDto(product);
        } catch (Exception e) {
            throw new NotFoundException("Product_does_not_exist");
        }

    }

    @Override
    public List<ProductDto> searchProduct(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public ProductDto createProduct(ProductReq req) {
        Product product = productMapper.toProduct(req);
        try {
            productRepository.save(product);
            return productMapper.toProductDto(product);
        } catch (Exception e) {
            throw new InternalServerException("Create_product_failed");
        }

    }

    @Override
    public ProductDto updateProduct(Long id, ProductReq req) {
        Optional<Product> find = productRepository.findById(id);
        if (find.isEmpty())
            throw new NotFoundException("Product_does_not_exist");
        Product product = productMapper.toProduct(req);
        product.setId(id);
        try {
            productRepository.save(product);
            return productMapper.toProductDto(product);
        } catch (Exception e) {
            throw new InternalServerException("Update_product_failed");
        }

    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> find = productRepository.findById(id);
        if (find.isEmpty())
            throw new NotFoundException("Product_does_not_exist");
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Delete_product_failed");
        }
    }

}
