package com.study.adminstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {

    @Id
    @Column(name = "files")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String fileOriName;
    private String fileurl;

    // Files : Member -> 1 : 1
    @OneToOne
    @JoinColumn(name="user_id")
    private Member member;
}
