package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryMysql implements CategoryRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Category create(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into category(type, title, created_at, created_by, updated_at, updated_by) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getType());
            ps.setString(2, category.getTitle());
            ps.setDate(3, Date.valueOf(category.getCreatedAt().toLocalDate()));
            ps.setString(4, category.getCreatedBy());
            ps.setDate(5, Date.valueOf(category.getUpdatedAt().toLocalDate()));
            ps.setString(6, category.getUpdatedBy());
            return ps;
        }, keyHolder);
        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Category findById(Long id) {
        return jdbcTemplate.queryForObject("select * from category where id = ?", new Object[]{id}, (resultSet, i) -> {
            Category category = new Category();
            category.setId(resultSet.getLong("id"));
            category.setType(resultSet.getString("type"));
            category.setTitle(resultSet.getString("title"));
            category.setCreatedAt(resultSet.getTimestamp(4).toLocalDateTime());
            category.setCreatedBy(resultSet.getString("created_by"));
            category.setUpdatedAt(resultSet.getTimestamp(6).toLocalDateTime());
            category.setUpdatedBy(resultSet.getString("updated_by"));
            return category;
        });
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
