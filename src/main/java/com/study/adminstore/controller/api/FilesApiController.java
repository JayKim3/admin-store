package com.study.adminstore.controller.api;

import com.study.adminstore.model.entity.Files;
import com.study.adminstore.service.FilesApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/files")
public class FilesApiController {

    @Autowired
    FilesApiService filesApiService;

    @PostMapping
    public String fileInsert(final HttpServletRequest request, @RequestPart final MultipartFile files) throws Exception {
        System.out.println(files);

        final Files file = new Files();

        final String sourceFileName = files.getOriginalFilename();
        final String fileUrl = "/Users/jaykim/IdeaProjects/admin-store/src/main/resources/static/images/";
        String destinationFileName;

        final File destinationFile = new File(fileUrl + sourceFileName);

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile); // 업로드할 파일 destinationFile로 지정

        file.setFilename(sourceFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);

        return "redirect:/mypage";
    }
 }
