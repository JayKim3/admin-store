package com.study.adminstore.controller.api;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.domain.Mail;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.service.EmailApiService;
import com.study.adminstore.service.MemberApiService;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberApiController implements CrudInterface<MemberApiRequest, MemberApiResponse> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberApiService memberApiService;

    @Autowired
    private EmailApiService emailApiService;

    @GetMapping("/emailCheck")
    @ResponseBody
    public String emailCheck(final String email) {
        // 이메일 중복 체크
        final String value = memberApiService.duplicateEmailCheck(email);
        return value;
    }

    @GetMapping("/logout")
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(final HttpServletRequest request, final HttpServletResponse response) {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(final HttpServletRequest request, final HttpServletResponse response) {
        return "signup";
    }

    @GetMapping("/find")
    public String findPage(final HttpServletRequest request, final HttpServletResponse response) {
        return "find";
    }

    @GetMapping("/mypage/{email}")
    public String myPage(@PathVariable final String email, final Model model) {
        model.addAttribute("member", memberApiService.findByEmail(email));
        return "mypage";
    }

    @PostMapping("/signup")
    @Override
    public ResponseEntity<MemberApiResponse> create(@RequestBody final MemberApiRequest memberApiRequest) {
        System.out.println(memberApiRequest);
        return memberApiService.create(memberApiRequest);
    }

    @PostMapping("/find")
    @ResponseBody
    public void emailSend(final String email) throws MessagingException {
        System.out.println(email);
        final Mail mail = emailApiService.createMailAndChangePassword(email);
        emailApiService.sendMail(mail);
    }

    @Override
    public ResponseEntity<MemberApiResponse> read(final Long id) {
        return null;
    }

    @Override
    public ResponseEntity<MemberApiResponse> update(final MemberApiRequest userApiRequest) {
        return null;
    }

    @Override
    public ResponseEntity<MemberApiResponse> delete(final Long id) {
        return null;
    }
}