package com.study.adminstore.service;

import com.study.adminstore.model.entity.Files;
import com.study.adminstore.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilesApiService {
    @Autowired
    FilesRepository filesRepository;

    public void save(final Files requestFile) {
        final Files f = new Files();
        f.setFilename(requestFile.getFilename());
        f.setFileOriName(requestFile.getFileOriName());
        f.setFileurl(requestFile.getFileurl());

        filesRepository.save(f);
    }
}
