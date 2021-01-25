package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.StoreApiRequest;
import com.study.adminstore.model.network.response.StoreApiResponse;

public class StoreApiService implements CrudInterface<StoreApiRequest, StoreApiResponse> {
    @Override
    public Header<StoreApiResponse> create(Header<StoreApiRequest> req) {
        return null;
    }

    @Override
    public Header<StoreApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<StoreApiResponse> update(Header<StoreApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
