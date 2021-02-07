package com.study.adminstore.controller.api;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.service.MemberApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/user")
public class MemberApiController implements CrudInterface<MemberApiRequest, MemberApiResponse> {

    @Autowired
    private MemberApiService memberApiService;

    @GetMapping("/logout")
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("")
    public String userload(final Model model) {
        System.out.println("user");
        model.addAttribute("members", memberApiService.findAll());
        return "admin/user";
    }

    @PostMapping("")
    @Override
    public ResponseEntity<MemberApiResponse> create(@RequestBody final MemberApiRequest memberApiRequest) {
        return memberApiService.create(memberApiRequest);
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