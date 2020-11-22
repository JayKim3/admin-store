package com.study.adminstore.service;

import com.study.adminstore.ifs.CountInterface;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.CategoryApiRequest;
import com.study.adminstore.model.network.request.PartnerApiRequest;
import com.study.adminstore.model.network.response.CategoryApiResponse;
import com.study.adminstore.model.network.response.PartnerApiResponse;
import com.study.adminstore.repository.CategoryRepositoryMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryApiService implements CrudInterface<CategoryApiRequest, CategoryApiResponse>, CountInterface {

    @Autowired
    private CategoryRepositoryMysql categoryRepositoryMysql;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> req) {
        return null;
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
}
