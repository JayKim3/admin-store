package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryMysql implements CategoryRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public int categoryCountAll() {
        return jdbcTemplate.queryForObject("select count(*) from category", Integer.class);
    }
}
