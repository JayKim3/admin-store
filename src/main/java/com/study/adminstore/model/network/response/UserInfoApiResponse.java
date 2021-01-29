package com.study.adminstore.model.network.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoApiResponse {
    private String email;
    private String password;

    private String auth;
}
