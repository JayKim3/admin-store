package com.study.adminstore.controller.api;

import com.study.adminstore.controller.CrudController;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.CategoryApiRequest;
import com.study.adminstore.model.network.response.CategoryApiResponse;
import com.study.adminstore.service.CategoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {
    @Autowired
    private CategoryApiService categoryApiService;

    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> req) {
        return categoryApiService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable Long id) {
        return categoryApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> req) {
        return categoryApiService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return categoryApiService.delete(id);
    }
}
