package com.study.adminstore.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CustomErrorController implements ErrorController {

    private final String ERROR_PATH = "/error/";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
            model.addAttribute("code", status.toString());
            model.addAttribute("msg", httpStatus.getReasonPhrase());
            model.addAttribute("timestamp", new Date());
        }
        return "error/404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
