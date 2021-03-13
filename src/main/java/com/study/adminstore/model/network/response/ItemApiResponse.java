package com.study.adminstore.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse {
    private Long id;
    private String category;
    private String name;
    private String content;
    private int price;
}
