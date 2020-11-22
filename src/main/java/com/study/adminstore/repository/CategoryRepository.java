package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    Category create(Category category);

    List<Category> findAll();

    Category update(Category category);

    void delete(Long id);

    int categoryCountAll();
}
