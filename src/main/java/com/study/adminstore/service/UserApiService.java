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
         System.out.println(userApiRequest);

         User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepositoryMysql.create(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read( Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update( Header<UserApiRequest> req) {
        return null;
    }

    @Override
    public Header delete( Long id) {
        return null;
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
