package com.study.adminstore.model.network.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoApiRequest {
    private String email;
    private String password;

    private String auth;
}
