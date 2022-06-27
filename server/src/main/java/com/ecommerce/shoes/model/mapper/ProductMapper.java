package com.ecommerce.shoes.model.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.entity.Category;
import com.ecommerce.shoes.entity.Product;
import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.request.ProductReq;
import com.ecommerce.shoes.service.BrandService;
import com.ecommerce.shoes.service.CategoryService;

@Component
public class ProductMapper {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    public Product toProduct(ProductReq req) {
        // convert req -> entity
        Product product = new Product();
        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setImage(req.getImage());
        product.setPrice(req.getPrice());

        // set createAt now
        product.setCreateAt(new Timestamp(System.currentTimeMillis()));
        // set brand
        Brand brand = new Brand();
        brand.setId(req.getBrandId());
        product.setBrand(brand);
        // set category
        ArrayList<Category> categories = new ArrayList<Category>();
        for (Integer id : req.getCategoryIds()) {
            Category category = new Category();
            category.setId(id);
            categories.add(category);
        }
        product.setCategories(categories);
        return product;
    }

    public ProductDto toProductDto(Product product) {
        // convert entity -> dto
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());

        // set brand
        Brand brand = brandService.getDetailBrand(product.getBrand().getId());
        productDto.setBrand(brand.getName());
        // set category
        ArrayList<String> categoriesName = new ArrayList<String>();
        for (Category category : product.getCategories()) {
            categoriesName.add(categoryService.getDetailCategory(category.getId()).getName());
        }
        productDto.setCategories(categoriesName);
        return productDto;
    }

    public List<ProductDto> toProductDtoList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        for (Product product : productList) {
            productDtoList.add(toProductDto(product));
        }
        return productDtoList;
    }
}