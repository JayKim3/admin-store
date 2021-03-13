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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void save(MultipartFile requestFile) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Member member = memberRepository.findByEmail(auth.getName());

        String sourceFileName = requestFile.getOriginalFilename();
        final String fileUrl = "/Users/sjk/IdeaProjects/admin-store/src/main/resources/static/images/";
        String destinationFileName;

        destinationFileName = member.getEmail() + "." + sourceFileName;
        File destinationFile = new File(fileUrl + destinationFileName);

        destinationFile.getParentFile().mkdirs();
        requestFile.transferTo(destinationFile); // 업로드할 파일 destinationFile로 지정

        Files profile = filesRepository.findByMemberId(member.getId());

        if (profile != null) {
            profile.setFilename(destinationFileName);
            profile.setFileOriName(sourceFileName);
            profile.setFileurl(fileUrl);
            profile.setMember(member);

            filesRepository.save(profile);
        } else {
            Files userImage = new Files();
            userImage.setFilename(destinationFileName);
            userImage.setFileOriName(sourceFileName);
            userImage.setFileurl(fileUrl);
            userImage.setMember(member);

            filesRepository.save(userImage);
        }
    }
}
