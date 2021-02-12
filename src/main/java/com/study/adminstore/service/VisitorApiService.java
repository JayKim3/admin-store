package com.study.adminstore.service;

import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.entity.Visitor;
import com.study.adminstore.repository.MemberRepository;
import com.study.adminstore.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class VisitorApiService {

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    MemberRepository memberRepository;

    public void save(final HttpServletRequest request, final Authentication authentication) {
        // request null 처리
        final WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();

        final Member member = memberRepository.findByEmail(authentication.getName());

        final Visitor visitor = new Visitor().builder()
                .sessionId(web.getSessionId())
                .ip(web.getRemoteAddress())
                .startVisit(new Date(request.getSession().getCreationTime()))
                .endVisit(new Date(request.getSession().getLastAccessedTime()))
                .agent(request.getHeader("User-Agent"))
                .path(request.getHeader("referer"))
                .member(member)
                .build();

        visitorRepository.save(visitor);

        // 아래의 getId를 로그아웃때 endVisit를 변경하기 위하여 세션에 따로 저장
        request.getSession().setAttribute("visitor_id", visitor.getId());
    }

    public ResponseEntity<Visitor> updateEndVisit(final HttpServletRequest httpServletRequest){
        final Long visitorId = (Long)httpServletRequest.getSession().getAttribute("visitor_id");
        final Optional<Visitor> optional = visitorRepository.findById(visitorId);

        return optional.map(visitor -> {
            visitor.setEndVisit(new Date(httpServletRequest.getSession().getLastAccessedTime()));
            return visitor;
        }).map(updateVisitor-> visitorRepository.save(updateVisitor))
                .map(updateVisitor-> new ResponseEntity<Visitor>(Visitor.builder().build(), HttpStatus.OK))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
