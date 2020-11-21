package com.study.adminstore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PartnerRepositoryMysql implements PartnerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int partnerCountAll() {
        return jdbcTemplate.queryForObject("select count(*) from partner", Integer.class);
    }
}
