package com.study.adminstore.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class VISITOR {

    @Id
    @GeneratedValue
    private Long id;

    private String sessionId;

    private String Ip;

    private String startVisit;

    private String endVisit;

    private String path;

    private String agent;

    private String country;

    // Member : VISITOR 1:1 => user_email로 매핑하는법 서치


}
