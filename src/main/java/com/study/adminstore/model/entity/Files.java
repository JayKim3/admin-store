package com.study.adminstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String fileOriName;
    private String fileurl;


    @OneToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
    private Item item;
}
