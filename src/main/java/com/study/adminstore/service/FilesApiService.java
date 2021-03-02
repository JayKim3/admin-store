package com.study.adminstore.service;

import com.study.adminstore.model.entity.Files;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.repository.FilesRepository;
import com.study.adminstore.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FilesApiService {
    @Autowired
    FilesRepository filesRepository;

    @Autowired
    MemberRepository memberRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void save(final MultipartFile requestFile) throws IOException {

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        final Member member = memberRepository.findByEmail(auth.getName());

        final String sourceFileName = requestFile.getOriginalFilename();
        final String fileUrl = "/Users/jaykim/IdeaProjects/admin-store/src/main/resources/static/images/";
        final String destinationFileName;

        destinationFileName = member.getEmail() + "." + sourceFileName;
        final File destinationFile = new File(fileUrl + destinationFileName);

        destinationFile.getParentFile().mkdirs();
        requestFile.transferTo(destinationFile); // 업로드할 파일 destinationFile로 지정

        final Files profile = filesRepository.findByMemberId(member.getId());

        if(profile != null) {
            profile.setFilename(destinationFileName);
            profile.setFileOriName(sourceFileName);
            profile.setFileurl(fileUrl);
            profile.setMember(member);

            filesRepository.save(profile);
        } else {
            final Files files = new Files();
            files.setFilename(destinationFileName);
            files.setFileOriName(sourceFileName);
            files.setFileurl(fileUrl);
            files.setMember(member);

            filesRepository.save(files);
        }
    }
}
