package com.study.adminstore.controller.api;

import com.study.adminstore.model.network.request.ItemApiRequest;
import com.study.adminstore.service.FilesApiService;
import com.study.adminstore.service.ItemApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/item")
public class ItemApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FilesApiService filesApiService;

    @Autowired
    ItemApiService itemApiService;

    @PostMapping
    public String create(@RequestParam("file") MultipartFile files, ItemApiRequest itempApiRequest) throws IOException {
//        logger.info("files : " + files);
        logger.info("itempApiRequest : " + itempApiRequest);
        itemApiService.create(files, itempApiRequest);
        return "admin/item";
    }
}
