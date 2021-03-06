package com.study.adminstore.config;

import com.study.adminstore.service.VisitorApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    VisitorApiService visitorApiService;

    @Override
    public void onLogoutSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                visitorApiService.updateEndVisit(httpServletRequest);
                httpServletRequest.getSession().invalidate();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        httpServletResponse.sendRedirect("/login");
    }
}
