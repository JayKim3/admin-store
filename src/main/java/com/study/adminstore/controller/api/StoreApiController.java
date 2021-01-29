package com.study.adminstore.controller.api;


import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.StoreApiRequest;
import com.study.adminstore.model.network.response.StoreApiResponse;
import com.study.adminstore.service.StoreApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
public class StoreApiController implements CrudInterface<StoreApiRequest, StoreApiResponse> {

    @Autowired
    private StoreApiService storeApiService;

    @Override
    @PostMapping("")
    public Header<StoreApiResponse> create(@RequestBody final Header<StoreApiRequest> req) {
        System.out.println(req);
        return storeApiService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header<StoreApiResponse> read(@PathVariable final Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<StoreApiResponse> update(@RequestBody final Header<StoreApiRequest> req) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable final Long id) {
        return null;
    }
}
