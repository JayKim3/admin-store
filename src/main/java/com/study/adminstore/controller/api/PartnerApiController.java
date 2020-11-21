package com.study.adminstore.controller.api;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.PartnerApiRequest;
import com.study.adminstore.model.network.response.PartnerApiResponse;
import com.study.adminstore.service.PartnerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/partner")
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerApiService partnerApiService;

    @Override
    @PostMapping("") // /api/partner
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> req) {
        return null;
    }

    @Override
    @GetMapping("{id}")   //  /api/partner/1 ... 1000
    public Header<PartnerApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> req) {
        return null;
    }

    @Override
    @DeleteMapping
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
