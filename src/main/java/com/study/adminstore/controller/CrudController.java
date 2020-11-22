package com.study.adminstore.controller;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.Header;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<Req, Res> implements CrudInterface {

    protected CrudInterface<Req, Res> baseService;

    @Override
    @PostMapping("")
    public Header create(@RequestBody Header req) {
        return baseService.create(req);
    }

    @Override
    @GetMapping("{id}")
    public Header read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping
    public Header update(@RequestBody Header req) {
        return baseService.update(req);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
