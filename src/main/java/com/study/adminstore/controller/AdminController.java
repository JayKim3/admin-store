package com.study.adminstore.controller;

import com.study.adminstore.service.CategoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryApiService categoryApiService;

    @PostConstruct
    public void init() {
//        this.userCount = userApiService.countAll();
//        this.storeCount = storeApiService.countAll();
    }

    @GetMapping("/")
    public String thymeleafTest(final Model model) {
//        model.addAttribute("userCount", userCount);
//        model.addAttribute("storeCount", storeCount);
        model.addAttribute("categoryCount", categoryApiService.count());
        model.addAttribute("categoryList", categoryApiService.findAll());
        return "master";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }
}
