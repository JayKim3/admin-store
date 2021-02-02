package com.study.adminstore.controller.api;


import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.request.StoreApiRequest;
import com.study.adminstore.model.network.response.StoreApiResponse;
import com.study.adminstore.service.StoreApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
public class StoreApiController implements CrudInterface<StoreApiRequest, StoreApiResponse> {

    @Autowired
    private StoreApiService storeApiService;

    @Override
    @PostMapping("")
    public ResponseEntity<StoreApiResponse> create(@RequestBody final StoreApiRequest req) {
        return storeApiService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<StoreApiResponse> read(@PathVariable final Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public ResponseEntity<StoreApiResponse> update(@RequestBody final StoreApiRequest req) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<StoreApiResponse> delete(@PathVariable final Long id) {
        return null;
    }
}
