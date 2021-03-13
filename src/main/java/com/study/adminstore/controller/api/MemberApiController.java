package com.study.adminstore.controller.api;

import com.study.adminstore.model.domain.Mail;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.service.EmailApiService;
import com.study.adminstore.service.MemberApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberApiService memberApiService;

    @Autowired
    private EmailApiService emailApiService;

    @GetMapping("/nav")
    @ResponseBody
    public String loadMainPage(String email) {
        Member member = memberApiService.findByEmail(email);
        if (member.getFiles() == null || member.getFiles().equals("")) return "Not Found";
        else
            return member.getFiles().getFileOriName();
    }

    @GetMapping("/emailCheck")
    @ResponseBody
    public String emailCheck(String email) {
        // 이메일 중복 체크
        String value = memberApiService.duplicateEmailCheck(email);
        return value;
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(HttpServletRequest request, HttpServletResponse response) {
        return "signup";
    }

    @GetMapping("/find")
    public String findPage(HttpServletRequest request, HttpServletResponse response) {
        return "find";
    }

    @GetMapping("/mypage/{email}")
    public String myPage(@PathVariable String email, Model model) {
        Member member = memberApiService.findByEmail(email);
        if (member == null || member.equals("")) return "redirect:/error";
        else {
            model.addAttribute("member", memberApiService.findByEmail(email));
            return "mypage";
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberApiResponse> create(@RequestBody MemberApiRequest memberApiRequest) {
        System.out.println(memberApiRequest);
        return memberApiService.create(memberApiRequest);
    }

    @PostMapping("/find")
    @ResponseBody
    public void emailSend(@RequestParam String email) throws MessagingException {
        System.out.println(email);
        Mail mail = emailApiService.createMailAndChangePassword(email);
        emailApiService.sendMail(mail);
        // mail update
    }
}