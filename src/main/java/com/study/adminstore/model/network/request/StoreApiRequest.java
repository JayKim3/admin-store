package com.study.adminstore.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreApiRequest {

    private Long id;

    private String account;

    private String password;

    private String name;

    private String status;

    private String address;

    private String businessNumber;

    private String ceoName;
}
