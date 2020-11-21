package com.study.adminstore.controller;

import com.study.adminstore.service.CategoryApiService;
import com.study.adminstore.service.PartnerApiService;
import com.study.adminstore.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserApiService userApiService;

    @Autowired
    private PartnerApiService partnerApiService;

    @Autowired
    private CategoryApiService categoryApiService;

    @GetMapping("/")
    public String thymeleafTest(Model model) {
        // Todo: Category(3), User count(2), Partner(1) 정보 Setting
        int userCount = userApiService.userCountAll();
        int partnerCount = partnerApiService.partnerCountAll();
        int categoryCount = categoryApiService.categoryCountAll();

        model.addAttribute("userCount", userCount);
        model.addAttribute("partnerCount", partnerCount);
        model.addAttribute("categoryCount", categoryCount);
        return "index";
    }
}
