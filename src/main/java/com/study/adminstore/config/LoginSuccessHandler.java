package com.study.adminstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        final WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();

        logger.info("Id : " + web.getSessionId());
        logger.info("Ip : " + web.getRemoteAddress());
        logger.info("s_visit : " + new Date(request.getSession().getCreationTime()));
        logger.info("e_visit : " + new Date(request.getSession().getLastAccessedTime())) ;
        logger.info("request : " + authentication.getName());
        logger.info("agent : " + request.getHeader("User-Agent"));
        logger.info("path : " + request.getHeader("referer"));
        response.sendRedirect("/");
        //로그인 성공시 필요한 작업 추가
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}