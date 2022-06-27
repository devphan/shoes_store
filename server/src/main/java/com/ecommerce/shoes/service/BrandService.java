package com.ecommerce.shoes.service;

import java.util.List;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.model.request.BrandReq;

public interface BrandService {
    List<Brand> getListBrand();

    Brand getDetailBrand(int id);

    Brand getBrandByName(String name);

    Brand createBrand(BrandReq req);

    Brand updateBrand(int id, BrandReq req);

    Brand deleteBrand(int id);
}
