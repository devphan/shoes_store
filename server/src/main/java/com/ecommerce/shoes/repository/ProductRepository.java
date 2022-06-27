package com.ecommerce.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrand(Brand brand);

    List<Product> findByNameContaining(String name);

    @Query(nativeQuery = true, value = "SELECT * \n" +
            " FROM product p \n" +
            "JOIN product_category pc \n" +
            "ON p.id = pc.product_id \n" +
            "JOIN category c \n" +
            "ON c.id = pc.category_id \n" +
            "WHERE c.name like :categoryName")
    List<Product> findByCategory(@Param("categoryName") String categoryName);

}
