package com.study.adminstore.ifs;

import com.study.adminstore.model.network.Header;
import org.springframework.http.ResponseEntity;

public interface CrudInterface<Req, Res> {
    // 모두 헤더를 달고 들어오기 때문에
    ResponseEntity<Res> create(Req req);
    ResponseEntity<Res> read(Long id);
    ResponseEntity<Res> update(Req req);
    ResponseEntity<Res> delete(Long id);
}
