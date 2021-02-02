package com.study.adminstore.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoApiRequest {
    private Long id;
    private String email;
    private String account;
    private String password;
    private String phoneNumber;
    private String auth;
}
