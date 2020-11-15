package com.study.adminstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/")
    public String thymeleafTest( Model model) {
        model.addAttribute("name", "ksj");
        return "index";
    }
}
