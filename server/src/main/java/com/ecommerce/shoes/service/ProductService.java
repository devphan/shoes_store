package com.ecommerce.shoes.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.entity.Product;
import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.request.ProductReq;

public interface ProductService {
    List<ProductDto> getListProduct();

    List<ProductDto> getPageProduct(int pageIndex, int pageSize);
    

    List<ProductDto> getListProductByBrand(Brand brand);

    List<ProductDto> searchProduct(String name);

    List<ProductDto> getListProductByCategory(String categoryName);

    ProductDto getDetailProduct(Long id);

    ProductDto createProduct(ProductReq req);

    ProductDto updateProduct(Long id, ProductReq req);

    void deleteProduct(Long id);
}
