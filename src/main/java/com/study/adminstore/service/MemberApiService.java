package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.domain.Role;
import com.study.adminstore.model.entity.Category;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.entity.Visitor;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.repository.MemberRepository;
import com.study.adminstore.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberApiService implements UserDetailsService, CrudInterface<MemberApiRequest, MemberApiResponse> {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    public ResponseEntity<MemberApiResponse> create(final MemberApiRequest memberApiRequest) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberApiRequest.setPassword(encoder.encode(memberApiRequest.getPassword()));

        System.out.println(memberApiRequest);

        final Member member = Member.builder()
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
    public ResponseEntity<MemberApiResponse> read(final Long id) {
        final Optional<Member> optional = memberRepository.findById(id);

        return optional.map(member-> {
            return new ResponseEntity(optional, HttpStatus.OK);
        })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<MemberApiResponse> update(final MemberApiRequest req) {
        final MemberApiRequest memberApiRequest = req;
        final Optional<Member> optional = memberRepository.findById(memberApiRequest.getId());

        return optional.map(member-> {
            member.setAccount(memberApiRequest.getAccount())
                    .setAuth(memberApiRequest.getAuth())
                    .setPhoneNumber(memberApiRequest.getPhoneNumber())
                    .setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy("UpdatedKSJ");
            return member;
        }).map(updateMember-> memberRepository.save(updateMember))
                .map(updateMember-> new ResponseEntity<MemberApiResponse>(MemberApiResponse.builder().build(), HttpStatus.OK))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<MemberApiResponse> delete(final Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final Member member = memberRepository.findByEmail(email);

        final List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin@gmail.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getAuth()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getAuth()));
        }

        return new User(member.getEmail(), member.getPassword(), authorities);
    }

    public String duplicateEmailCheck(final String email) {

        if(!memberRepository.existsByEmail(email)) {
            return "JOIN";
        } else {
            return "EXIST";
        }
    }

    public List<Member> findAll(final int page) {
        final Pageable pageable = PageRequest.of(page, 8  , Sort.by(Sort.Direction.ASC, "id"));
        final Page<Member> all = memberRepository.findAll(pageable);
        final List<Member> members = all.getContent();
        return members;
    }

    public List<Member> currentMonthUser() {
        return memberRepository.findCurrentMonthUser();
    }

    public List<Member> currentYearlyUser() {
        return memberRepository.findCurrentYearlyUser();
    }

    public ArrayList<String> currentCountryUser() {return visitorRepository.findCurrentCountryUser();}

    public ArrayList<Integer> currrentContinueTime() {return visitorRepository.findLoginContinueTime();}

    public Member findByEmail(final String email) {
        final Member member = memberRepository.findByEmail(email);
        return member;
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

    public ResponseEntity<Member> deleteById(final Long id) {
        final Optional<Member> optional = memberRepository.findById(id);

        return optional.map(member-> {
            memberRepository.delete(member);
            return new ResponseEntity(HttpStatus.OK);
        })
        .orElseGet(()->ResponseEntity.notFound().build());
    }
}
