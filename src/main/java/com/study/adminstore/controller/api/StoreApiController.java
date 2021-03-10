package com.study.adminstore.controller.api;


import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.request.StoreApiRequest;
import com.study.adminstore.model.network.response.StoreApiResponse;
import com.study.adminstore.service.StoreApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 차후에 master 도메인으로 변경 (master권한을 가지고 있는 사람만 해당 api함수를 사용할 수 있게)
@RestController
@RequestMapping("/admin/store")
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
        return storeApiService.read(id);
    }

    @Override
    @PutMapping("")
    public ResponseEntity<StoreApiResponse> update(@RequestBody final StoreApiRequest req) {
        return storeApiService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<StoreApiResponse> delete(@PathVariable final Long id) {
        return storeApiService.delete(id);
    }
}
