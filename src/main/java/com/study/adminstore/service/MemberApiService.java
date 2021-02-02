package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.domain.Role;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberApiService implements UserDetailsService, CrudInterface<MemberApiRequest, MemberApiResponse> {

    @Autowired
    private MemberRepository memberRepository;

    public ResponseEntity<MemberApiResponse> create(final MemberApiRequest memberApiRequest) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberApiRequest.setPassword(encoder.encode(memberApiRequest.getPassword()));

        Member member = Member.builder()
            .account(memberApiRequest.getAccount())
            .password(memberApiRequest.getPassword())
            .auth(memberApiRequest.getAuth())
            .email(memberApiRequest.getEmail())
            .phoneNumber(memberApiRequest.getPhoneNumber())
            .createdAt(LocalDateTime.now())
            .createdBy("KSJ")
            .registeredAt(LocalDateTime.now())
            .unregisteredAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .updatedBy("KSJ")
            .build();

        memberRepository.save(member);

        return new ResponseEntity<MemberApiResponse>(response(member), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MemberApiResponse> read(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<MemberApiResponse> update(MemberApiRequest memberApiRequest) {
        return null;
    }

    @Override
    public ResponseEntity<MemberApiResponse> delete(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin@gmail.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getAuth()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getAuth()));
        }

        return new User(member.getEmail(), member.getPassword(), authorities);
    }

    private MemberApiResponse response(final Member member) {
        final MemberApiResponse memberApiResponse = MemberApiResponse.builder()
                .id(member.getId())
                .account(member.getAccount())
                .password(member.getPassword())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .auth(member.getAuth())
                .build();

        return memberApiResponse;
    }
}
