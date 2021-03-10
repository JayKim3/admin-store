package com.study.adminstore.model.network.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModifyApiResponse {
    private String email;
    private String account;
    private String phoneNumber;
    private String auth;
}
