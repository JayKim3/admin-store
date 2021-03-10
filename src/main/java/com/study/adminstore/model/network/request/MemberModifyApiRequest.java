package com.study.adminstore.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModifyApiRequest {
    private String email;
    private String account;
    private String phoneNumber;
    private String auth;
}
