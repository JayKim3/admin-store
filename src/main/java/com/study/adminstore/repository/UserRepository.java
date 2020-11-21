package com.study.adminstore.repository;

import com.study.adminstore.model.entity.User;

import java.util.List;

public interface UserRepository {
    User create(User user);

    User findById(Long id);

    User update(User user);

    List<User> findAll();

    void deleteById(Long id);
}
