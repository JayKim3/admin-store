package com.study.adminstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroupList"})
@Builder
@Accessors(chain = true) // 객체를 체이닝형태로 생성하거나 수정 가능
@Entity
public class Member {
    // Java -> Camel Case, DB -> Snake Case

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String account;

    private String password;

    private String auth;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // Member : OrderGroup -> 1 : N

    // Files : Member -> 1 : 1
    @OneToOne(mappedBy="member")
    @JsonIgnore
    private Files files;
}
