package com.study.adminstore.service;

import com.study.adminstore.model.entity.User;
import com.study.adminstore.model.network.request.UserInfoApiRequest;
import com.study.adminstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserApiService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Long create(final UserInfoApiRequest userInfoApiRequest) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfoApiRequest.setPassword(encoder.encode(userInfoApiRequest.getPassword()));

        return userRepository.save(User.builder()
        .account(userInfoApiRequest.getAccount())
        .password(userInfoApiRequest.getPassword())
        .auth(userInfoApiRequest.getAuth())
        .email(userInfoApiRequest.getEmail())
        .phoneNumber(userInfoApiRequest.getPhoneNumber())
        .createdAt(LocalDateTime.now())
        .createdBy("KSJ")
        .registeredAt(LocalDateTime.now())
        .unregisteredAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .updatedBy("KSJ")
        .build()).getId();
    }

    @Override
    public User loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

//    private Header<UserApiResponse> response(final User user) {
//        final UserApiResponse userApiResponse = UserApiResponse.builder()
//                .id(user.getId())
//                .account(user.getAccount())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .phoneNumber(user.getPhoneNumber())
//                .status(user.getStatus())
//                .registeredAt(user.getRegisteredAt())
//                .unregisteredAt(user.getUnregisteredAt())
//                .build();
//
//        return Header.OK(userApiResponse);
//    }
}
