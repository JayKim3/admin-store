package com.study.adminstore.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryApiRequest {
    private Long id;

    private String parentType;

    private String type;

    private String title;
}
