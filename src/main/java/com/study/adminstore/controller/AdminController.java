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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryApiService categoryApiService;

    @Autowired
    private MemberApiService memberApiService;

    @GetMapping("/user/{page}")
    public String userload(@PathVariable int page, Model model) {
        model.addAttribute("members", memberApiService.findAll(page));
        model.addAttribute("pages", 10);
        return "admin/user";
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

    @GetMapping("/continueTime")
    @ResponseBody
    public ArrayList<Integer> findLoginContinueTime() {return memberApiService.currrentContinueTime();}

    @DeleteMapping("/user")
    @ResponseBody
    public int delete(@RequestParam(value = "deleteUserArray[]") Long[] deleteUserArray) {
        int result = 1;
        try {
            for (int i = 0; i < deleteUserArray.length; i++) memberApiService.deleteById(deleteUserArray[i]);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    // edit 버튼 선택 시 팝업에 데이터 뿌려주기 위해 가져오는 api
    @GetMapping("/user/modify/{id}")
    public ResponseEntity user(@PathVariable Long id, Model model) {
        ResponseEntity<MemberApiResponse> member = memberApiService.read(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // 실제로 정보 수정 후 modify 버튼 누를 시 동작되는 api
    @PutMapping("/user")
    public ResponseEntity update(@RequestBody MemberApiRequest memberApiRequest) {
        ResponseEntity<MemberApiResponse> member = memberApiService.update(memberApiRequest);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/")
    public void loadUserProfile(Model model) {
        System.out.println("loadUserProfile");
        Member member = memberApiService.findByAuth();
        model.addAttribute("member", member);
    }
}

