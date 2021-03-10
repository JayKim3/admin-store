package com.study.adminstore.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreApiResponse {
    private String account;

    private String password;

    private String name;

    private String status;

    private String address;

    private String businessNumber;

    private String ceoName;
}
