package com.study.adminstore.repository;

import com.study.adminstore.model.entity.User;

public interface UserRepository {
    User create(User user);

    User findById(Long id);
}
