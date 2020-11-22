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

    private int userCount;
    private int partnerCount;
    private int categoryCount;

    @Autowired
    private UserApiService userApiService;

    @Autowired
    private PartnerApiService partnerApiService;

    @Autowired
    private CategoryApiService categoryApiService;

    @PostConstruct
    public void init() {
        this.userCount = userApiService.countAll();
        this.partnerCount = partnerApiService.countAll();
        this.categoryCount = categoryApiService.countAll();
    }

    @GetMapping("/")
    public String thymeleafTest(Model model) {
        model.addAttribute("userCount", userCount);
        model.addAttribute("partnerCount", partnerCount);
        model.addAttribute("categoryCount", categoryCount);
        return "index";
    }
}
