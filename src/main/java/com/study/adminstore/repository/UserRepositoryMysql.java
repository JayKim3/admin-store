package com.study.adminstore.repository;

import com.study.adminstore.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;


@Repository
public class UserRepositoryMysql implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User create( User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection-> {
            PreparedStatement ps = connection.prepareStatement("insert into user(account, password, status, email, phone_number, registered_at, unregistered_at, created_at, created_by, updated_at, updated_by) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getStatus());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNumber());
            ps.setDate(6, Date.valueOf(user.getRegisteredAt().toLocalDate()));
            ps.setDate(7, Date.valueOf(user.getUnregisteredAt().toLocalDate()));
            ps.setDate(8, Date.valueOf(user.getCreatedAt().toLocalDate()));
            ps.setString(9, user.getCreatedBy());
            ps.setDate(10, Date.valueOf(user.getUpdatedAt().toLocalDate()));
            ps.setString(11, user.getUpdatedBy());
            return ps;
         }, keyHolder);
        return findById(keyHolder.getKey().longValue());
    }


    @Override
    // TODO: queryForObject 말고 다른 함수로 하나의 컬럼 리턴하는 방법 서치
    public User findById(Long id) {
        System.out.println("id : " +id);
       return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setStatus(resultSet.getString("status"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setRegisteredAt(resultSet.getTimestamp(7).toLocalDateTime());
            user.setUnregisteredAt(resultSet.getTimestamp(8).toLocalDateTime());
            user.setCreatedAt(resultSet.getTimestamp(9).toLocalDateTime());
            user.setCreatedBy(resultSet.getString("created_by"));
            user.setUpdatedAt(resultSet.getTimestamp(11).toLocalDateTime());
            user.setUpdatedBy(resultSet.getString("updated_by"));
            return user;
        });
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("update user set account = ?, password = ?, status =?, email =?, phone_number = ?, registered_at = ?, unregistered_at = ? where id = ?");
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getStatus());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNumber());
            ps.setDate(6, Date.valueOf(user.getRegisteredAt().toLocalDate()));
            ps.setDate(7, Date.valueOf(user.getUnregisteredAt().toLocalDate()));
            ps.setLong(8, user.getId());
            return ps;
        });
        return findById(user.getId());
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from user where id = ?", preparedStatement -> {
            preparedStatement.setLong(1, id);
        });
    }

    @Override
    public int userCountAll() {
        return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }
}
