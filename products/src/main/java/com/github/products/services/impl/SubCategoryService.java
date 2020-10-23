package com.github.products.services.impl;

import com.github.products.entity.EntityStatus;
import com.github.products.entity.SubCategory;
import com.github.products.exceptions.NotFound;
import com.github.products.repository.SubCategoryRepo;
import com.github.products.services.ISubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryService implements ISubCategoryService {

    private final SubCategoryRepo subCategoryRepo;

    @Override
    public SubCategory create(SubCategory subCategory) {
        return this.subCategoryRepo.save(subCategory);
    }

    @Override
    public List<SubCategory> readAllByStatus(EntityStatus status) {
        return this.subCategoryRepo.findAllByStatus(status);
    }

    @Override
    public SubCategory readByName(String name) {
        return this.subCategoryRepo.findByName(name)
                .orElseThrow(NotFound::new);
    }

    @Override
    public SubCategory readById(Long id) {
        return this.subCategoryRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public List<SubCategory> readAllByCategoryName(String categoryName) {
        return this.subCategoryRepo.findAllByStatusAndCategoryName(
                EntityStatus.on, categoryName
        );
    }

    @Override
    public void update(SubCategory subCategory) {
        this.subCategoryRepo.save(subCategory);
    }

    @Override
    public void delete(Long id) {
        this.subCategoryRepo.updateStatus(id, EntityStatus.off);
    }
}