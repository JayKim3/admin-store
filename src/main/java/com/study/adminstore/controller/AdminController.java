package com.study.adminstore.controller;

import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.entity.Visitor;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.service.CategoryApiService;
import com.study.adminstore.service.MemberApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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

    @GetMapping("/user/{page}")
    public String userload(@PathVariable final int page, final Model model) {
        model.addAttribute("members", memberApiService.findAll(page));
        model.addAttribute("pages", 10);
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

    @GetMapping("/countryUser")
    @ResponseBody
    public ArrayList<String> currentCountryUser() { return memberApiService.currentCountryUser(); }

    @GetMapping("/user/delete")
    @ResponseBody
    public int delete(@RequestParam(value = "deleteUserArray[]") final Long[] deleteUserArray) {
        int result = 1;
        try {
            for (int i = 0; i < deleteUserArray.length; i++) {
                memberApiService.deleteById(deleteUserArray[i]);
            }
        }catch(final Exception e) {
            result=0;
        }
        return result;
    }

    @GetMapping("/user/modify/{id}")
    public ResponseEntity user(@PathVariable final Long id, final Model model) {
        final ResponseEntity<MemberApiResponse> member = memberApiService.read(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/user/modify")
    public ResponseEntity update(@RequestBody final MemberApiRequest memberApiRequest) {
        System.out.println(memberApiRequest);
        final ResponseEntity<MemberApiResponse> member = memberApiService.update(memberApiRequest);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
