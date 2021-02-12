package com.study.adminstore.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroupList"})
@Getter
@Builder
@Accessors(chain = true) // 객체를 체이닝형태로 생성하거나 수정 가능
@Entity
public class Member {
    // Java -> Camel Case, DB -> Snake Case

    @Id
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

    // User : OrderGroup -> 1 : N

    // Files : Member -> 1 : 1
    @OneToOne
    @JoinColumn(name = "files_id")
    private Files files;

}
