package com.study.adminstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/")
    public String thymeleafTest( Model model) {
        // Todo: Category(3), User count(2), Partner(1) 정보 Setting
        model.addAttribute("name", "ksj");
        return "index";
    }
}
