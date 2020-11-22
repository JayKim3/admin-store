package com.study.adminstore.service;

import com.study.adminstore.ifs.CountInterface;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.Partner;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.PartnerApiRequest;
import com.study.adminstore.model.network.response.PartnerApiResponse;
import com.study.adminstore.repository.PartnerRepositoryMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerApiService implements CrudInterface<PartnerApiRequest, PartnerApiResponse>, CountInterface {

    @Autowired
    PartnerRepositoryMysql partnerRepositoryMysql;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> req) {

        PartnerApiRequest body = req.getData();

        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    @Override
    public int countAll() {
        return partnerRepositoryMysql.partnerCountAll();
    }
}
