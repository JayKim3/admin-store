package com.study.adminstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        final WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();

        System.out.println("Id : " + web.getSessionId());
        System.out.println("Ip : " + web.getRemoteAddress());
        System.out.println("s_visit : " + new Date(request.getSession().getCreationTime()));
        System.out.println("e_visit : " + new Date(request.getSession().getLastAccessedTime())) ;
        System.out.println("request : " + authentication.getName());
        System.out.println("agent : " + request.getHeader("User-Agent"));
        System.out.println("path : " + request.getHeader("referer"));
        response.sendRedirect("/");
        //로그인 성공시 필요한 작업 추가
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}