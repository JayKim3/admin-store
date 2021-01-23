package com.study.adminstore.controller;

import com.study.adminstore.service.CategoryApiService;
import com.study.adminstore.service.PartnerApiService;
import com.study.adminstore.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class AdminController {

    @Autowired
    CategoryApiService categoryApiService;

    @PostConstruct
    public void init() {
//        this.userCount = userApiService.countAll();
//        this.partnerCount = partnerApiService.countAll();
    }

    @GetMapping("/")
    public String thymeleafTest(Model model) {
//        model.addAttribute("userCount", userCount);
//        model.addAttribute("partnerCount", partnerCount);
        model.addAttribute("categoryCount", categoryApiService.count());
        return "index";
    }
}
