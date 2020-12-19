package com.study.adminstore.ifs;

import com.study.adminstore.model.network.Header;

public interface CrudInterface<Req, Res> {
    // 모두 헤더를 달고 들어오기 때문에
    Header<Res> create(Header<Req> req);
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> req);
    Header delete(Long id);
}
