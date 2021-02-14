package com.study.adminstore.service;

import com.study.adminstore.AdminStoreApplicationTests;
import com.study.adminstore.model.entity.Member;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberApiServiceTest extends AdminStoreApplicationTests {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private MemberApiService memberApiService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void currentMonthUser() {
        final List<Member> members = memberApiService.currentMonthUser();
        assertThat(members).isNotNull();
        logger.info("currentMonthUsers : " + members);
    }
}
