package com.study.adminstore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryMysql implements CategoryRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int categoryCountAll() {
        return jdbcTemplate.queryForObject("select count(*) from category", Integer.class);
    }
}
