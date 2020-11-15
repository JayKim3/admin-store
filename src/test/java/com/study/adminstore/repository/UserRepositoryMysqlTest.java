package com.study.adminstore.repository;

import com.study.adminstore.AdminStoreApplicationTests;
import com.study.adminstore.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


public class UserRepositoryMysqlTest extends AdminStoreApplicationTests {

    @Autowired
    private UserRepositoryMysql userRepositoryMysql;

    @Test
    public void create() {
         User user = User.builder()
                .account("ksj01")
                .password("ksj01")
                .status("REGISTERED")
                .email("ragamuffin6701@gmail.com")
                .phoneNumber("010-3574-0001")
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                 .createdAt(LocalDateTime.now())
                 .createdBy("AdminServer")
                 .updatedAt(LocalDateTime.now())
                 .updatedBy("AdminServer")
                .build();

         User newUser = userRepositoryMysql.create(user);

         System.out.println(newUser);
    }
}

