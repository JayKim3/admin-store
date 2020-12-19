package com.study.adminstore.service;

import com.study.adminstore.ifs.CountInterface;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.Category;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.CategoryApiRequest;
import com.study.adminstore.model.network.request.PartnerApiRequest;
import com.study.adminstore.model.network.response.CategoryApiResponse;
import com.study.adminstore.model.network.response.PartnerApiResponse;
import com.study.adminstore.repository.CategoryRepositoryMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CategoryApiService implements CrudInterface<CategoryApiRequest, CategoryApiResponse>, CountInterface {

    @Autowired
    private CategoryRepositoryMysql categoryRepositoryMysql;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> req) {
        // request data
        // category create
        // CategoryApiResponse return

        CategoryApiRequest categoryApiRequest = req.getData();

        System.out.println(categoryApiRequest);

        String type = categoryApiRequest.getType();
        String title = categoryApiRequest.getTitle();

        if(type.equals("") || title.equals("")) return Header.ERROR("failed");

        Category category = Category.builder()
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .createdAt(LocalDateTime.now())
                .createdBy("KSJ")
                .updatedAt(LocalDateTime.now())
                .updatedBy("KSJ")
                .build();

        // 이미 존재하는 카테고리일 경우 에러처리

        categoryRepositoryMysql.create(category);

        return response(category);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    @Override
    public int countAll() {
        return categoryRepositoryMysql.categoryCountAll();
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .type(category.getType())
                .title(category.getTitle())
                .build();

        return Header.OK(categoryApiResponse);
    }
}
