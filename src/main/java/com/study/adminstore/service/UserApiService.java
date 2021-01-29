package com.study.adminstore.service;

import com.study.adminstore.ifs.CountInterface;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.User;
import com.study.adminstore.model.entity.UserInfo;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.UserInfoApiRequest;
import com.study.adminstore.model.network.response.UserApiResponse;
import com.study.adminstore.model.network.request.UserApiRequest;
import com.study.adminstore.model.network.response.UserInfoApiResponse;
import com.study.adminstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserApiService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    public UserInfoApiResponse create(final UserInfoApiRequest req) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final UserInfoApiRequest userInfoApiRequest = req;
        userInfoApiRequest.setPassword(encoder.encode(userInfoApiRequest.getPassword()));

        return userRepository.save(UserInfo.builder()
        .email(req.getEmail())
        .auth(req.getAuth())
        .password(req.getPassword()).build()).getCode();
    }

    @Override
    public UserInfo loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    private Header<UserApiResponse> response(final User user) {
        final UserApiResponse userApiResponse = UserApiResponse.builder()
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
