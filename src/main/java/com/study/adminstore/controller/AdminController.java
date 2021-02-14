package com.study.adminstore.controller;

import com.study.adminstore.model.entity.Member;
import com.study.adminstore.service.CategoryApiService;
import com.study.adminstore.service.MemberApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryApiService categoryApiService;

    @Autowired
    private MemberApiService memberApiService;

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

    @GetMapping("/user")
    public String userload(final Model model) {
        System.out.println("user");
        model.addAttribute("members", memberApiService.findAll());
        return "admin/user";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }

    @GetMapping("/monthlyUser")
    @ResponseBody
    public List<Member> currentMonthUser() {
        return memberApiService.currentMonthUser();
    }

    @GetMapping("/yearlyUser")
    @ResponseBody
    public List<Member> currentYearlyUser() {
        return memberApiService.currentYearlyUser();
    }
}
