package com.study.adminstore.service;

import com.study.adminstore.model.domain.Role;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.network.request.MemberApiRequest;
import com.study.adminstore.model.network.request.MemberModifyApiRequest;
import com.study.adminstore.model.network.response.MemberApiResponse;
import com.study.adminstore.model.network.response.MemberModifyApiResponse;
import com.study.adminstore.repository.MemberRepository;
import com.study.adminstore.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberApiService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    public ResponseEntity<MemberApiResponse> create(MemberApiRequest memberApiRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberApiRequest.setPassword(encoder.encode(memberApiRequest.getPassword()));

        System.out.println(memberApiRequest);

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

        return new ResponseEntity<>(response(member), HttpStatus.OK);

    }

    public ResponseEntity<MemberApiResponse> read(Long id) {
        Optional<Member> optional = memberRepository.findById(id);

        return optional.map(member -> {
            return new ResponseEntity(optional, HttpStatus.OK);
        })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<MemberModifyApiResponse> update(MemberModifyApiRequest req) {
        MemberModifyApiRequest memberModifyApiRequest = req;
        Optional<Member> optional = Optional.ofNullable(memberRepository.findByEmail(memberModifyApiRequest.getEmail()));

        return optional.map(member -> {
            member.setAccount(memberModifyApiRequest.getAccount())
                    .setAuth(memberModifyApiRequest.getAuth())
                    .setPhoneNumber(memberModifyApiRequest.getPhoneNumber())
                    .setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy("UpdatedKSJ");
            return member;
        }).map(updateMember -> memberRepository.save(updateMember))
                .map(updateMember -> new ResponseEntity<>(MemberModifyApiResponse.builder().build(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<MemberApiResponse> delete(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) throw new UsernameNotFoundException(email);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@gmail.com").equals(email)) authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getAuth()));
        else
            authorities.add(new SimpleGrantedAuthority(Role.USER.getAuth()));

        return new User(member.getEmail(), member.getPassword(), authorities);
    }

    public String duplicateEmailCheck(String email) {

        if (!memberRepository.existsByEmail(email)) return "JOIN";
        else return "EXIST";
    }

    public List<Member> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by(Sort.Direction.ASC, "id"));
        Page<Member> all = memberRepository.findAll(pageable);
        List<Member> members = all.getContent();
        return members;
    }

    public List<Member> currentMonthUser() {
        return memberRepository.findCurrentMonthUser();
    }

    public List<Member> currentYearlyUser() {
        return memberRepository.findCurrentYearlyUser();
    }

    public ArrayList<String> currentCountryUser() {
        return visitorRepository.findCurrentCountryUser();
    }

    public ArrayList<Integer> currrentContinueTime() {
        return visitorRepository.findLoginContinueTime();
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    public Member findByAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(auth.getName());
        return member;
    }

    private MemberApiResponse response(Member member) {
        MemberApiResponse memberApiResponse = MemberApiResponse.builder()
                .id(member.getId())
                .account(member.getAccount())
                .password(member.getPassword())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .auth(member.getAuth())
                .build();

        return memberApiResponse;
    }

    public ResponseEntity<Member> deleteById(Long id) {
        Optional<Member> optional = memberRepository.findById(id);

        return optional.map(member -> {
            memberRepository.delete(member);
            return new ResponseEntity(HttpStatus.OK);
        })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
