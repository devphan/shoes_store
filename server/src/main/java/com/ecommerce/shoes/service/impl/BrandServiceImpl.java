package com.ecommerce.shoes.service.impl;

import java.util.List;
import java.util.Optional;

import com.ecommerce.shoes.entity.Brand;
import com.ecommerce.shoes.entity.Product;
import com.ecommerce.shoes.exception.DuplicateRecordException;
import com.ecommerce.shoes.exception.InternalServerException;
import com.ecommerce.shoes.exception.NotFoundException;
import com.ecommerce.shoes.model.request.BrandReq;
import com.ecommerce.shoes.repository.BrandRepository;
import com.ecommerce.shoes.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getListBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getDetailBrand(int id) {
        try {
            return brandRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException("Brand_does_not_exist");
        }

    }

    @Override
    public Brand getBrandByName(String name) {
        try {
            return brandRepository.findByName(name);
        } catch (Exception e) {
            throw new NotFoundException("Brand_does_not_exist");
        }
    }

    @Override
    public Brand createBrand(BrandReq req) {
        Brand find = brandRepository.findByName(req.getName());
        if (find != null)
            throw new DuplicateRecordException("Brand_already_exist");
        Brand brand = new Brand();
        brand.setName(req.getName());
        brand.setThumbnail(req.getThumbnail());
        try {
            return brandRepository.save(brand);
        } catch (Exception e) {
            throw new InternalServerException("Create_brand_failed");
        }

    }

    @Override
    public Brand updateBrand(int id, BrandReq req) {
        Optional<Brand> find = brandRepository.findById(id);
        if (find.isEmpty())
            throw new NotFoundException("Brand_does_not_exist");
        Brand brand = find.get();
        brand.setName(req.getName());
        brand.setThumbnail(req.getThumbnail());
        try {
            return brandRepository.save(brand);
        } catch (Exception e) {
            throw new InternalServerException("Update_brand_failed");
        }
    }

    @Override
    public Brand deleteBrand(int id) {
        Optional<Brand> find = brandRepository.findById(id);
        if (find.isEmpty())
            throw new NotFoundException("Brand_does_not_exist");
        try {
            brandRepository.deleteById(id);
            return find.get();
        } catch (Exception e) {
            throw new InternalServerException("Delete_brand_failed");
        }

    }

}
