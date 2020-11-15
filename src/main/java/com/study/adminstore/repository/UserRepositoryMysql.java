package com.study.adminstore.repository;

import com.study.adminstore.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;


@Repository
public class UserRepositoryMysql implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection-> {
            PreparedStatement ps = connection.prepareStatement("insert into user(account, password, status, email, phone_number, registered_at, unregistered_at, created_at, created_by, updated_at, updated_by) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getStatus());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNumber());
            ps.setDate(6, java.sql.Date.valueOf(user.getRegisteredAt().toLocalDate()));
            ps.setDate(7, java.sql.Date.valueOf(user.getUnregisteredAt().toLocalDate()));
            ps.setDate(8, java.sql.Date.valueOf(user.getCreatedAt().toLocalDate()));
            ps.setString(9, user.getCreatedBy());
            ps.setDate(10, java.sql.Date.valueOf(user.getUpdatedAt().toLocalDate()));
            ps.setString(11, user.getUpdatedBy());
            return ps;
         }, keyHolder);
        return findById(keyHolder.getKey().longValue());
    }


    @Override
    public User findById(Long id) {
        System.out.println(id);
        return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, (resultSet, i) -> {
            User user = new User();
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setStatus(resultSet.getString("status"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setRegisteredAt(resultSet.getTimestamp(7).toLocalDateTime());
            user.setUnregisteredAt(resultSet.getTimestamp(8).toLocalDateTime());
            System.out.println(user);
            return user;
        });
    }
}
