package com.study.adminstore.controller.api;

import com.study.adminstore.controller.CrudController;
import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.User;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.response.UserApiResponse;
import com.study.adminstore.model.network.request.UserApiRequest;
import com.study.adminstore.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiService userApiService;

    @PostConstruct
    public void init() {
        this.baseService = userApiService;
    }
}
