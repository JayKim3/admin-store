package com.study.adminstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Visitor {

    @Id
    @Column(name = "visitor_id")
    @GeneratedValue
    private Long id;

    private String sessionId;

    private String ip;

    private Date startVisit;

    private Date endVisit;

    private String path;

    private String agent;

    private String country;

    // Member : VISITOR 1:N => user_email로 매핑하는법 서치
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
