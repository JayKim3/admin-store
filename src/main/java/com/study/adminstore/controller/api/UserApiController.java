package com.study.adminstore.controller.api;

import com.study.adminstore.model.network.request.UserInfoApiRequest;
import com.study.adminstore.service.UserApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserApiController {

    @Autowired
    private UserApiService userApiService;

    @PostMapping("")
    public String create(@RequestBody final UserInfoApiRequest userInfoApiRequest) {
        System.out.println(userInfoApiRequest);
        userApiService.create(userInfoApiRequest);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}