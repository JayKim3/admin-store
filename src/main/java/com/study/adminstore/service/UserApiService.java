package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.User;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.response.UserApiResponse;
import com.study.adminstore.model.network.request.UserApiRequest;
import com.study.adminstore.repository.UserRepositoryMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepositoryMysql userRepositoryMysql;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> req) {
        // request data
        // user create
        // UserApiResponse return
         UserApiRequest userApiRequest = req.getData();

         User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .build();

        User newUser = userRepositoryMysql.create(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne, getById

        return Optional.ofNullable(userRepositoryMysql.findById(id))
                .map(user-> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> req) {
        // 1. data
        UserApiRequest userApiRequest = req.getData();
        // 2. id -> user
        Optional<User> optional = Optional.ofNullable(userRepositoryMysql.findById(userApiRequest.getId()));

        return optional.map(user-> {
            // 3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            // 4. userApiResponse
            return user;
        })
            .map(user-> userRepositoryMysql.update(user))
            .map(updateUser-> response(updateUser))
            .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = Optional.ofNullable(userRepositoryMysql.findById(id));

        return optional.map((user)->{
                userRepositoryMysql.deleteById(id);
                return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public List<User> findAll() {
        return null;
    }

    public int userCountAll() {
        return userRepositoryMysql.userCountAll();
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
