package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    Category create(Category category);

    Category findById(Long id);

    List<Category> findAll();

    Category update(Category category);

    void delete(Long id);

    int categoryCountAll();
}
