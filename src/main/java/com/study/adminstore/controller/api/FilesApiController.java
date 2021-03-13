package com.study.adminstore.controller.api;

import com.study.adminstore.service.FilesApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FilesApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FilesApiService filesApiService;

    @PostMapping
    public String create(@RequestPart MultipartFile files) throws Exception {
        filesApiService.save(files);
        return "redirect:/";
    }
}
