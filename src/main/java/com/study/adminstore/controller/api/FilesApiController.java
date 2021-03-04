package com.study.adminstore.controller.api;

import com.study.adminstore.model.entity.Files;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.service.FilesApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/files")
public class FilesApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FilesApiService filesApiService;

    @PostMapping
    public String create(final HttpServletRequest request, @RequestPart final MultipartFile files) throws Exception {
        logger.info("files : " + files);
        filesApiService.save(files);
        return "redirect:/mypage";
    }


 }
