package com.study.adminstore.controller;

import com.study.adminstore.model.network.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GetController {

    @GetMapping("/header")
    public Header getHeader() {

        // {"resultCode: "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}