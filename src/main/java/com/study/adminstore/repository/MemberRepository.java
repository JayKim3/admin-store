package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM member WHERE (created_at > LAST_DAY(NOW() - interval 1 month) AND created_at <= LAST_DAY(NOW()))" , nativeQuery = true)
    List<Member> findCurrentMonthUser();

    @Query(value = "SELECT * FROM member WHERE (YEAR(NOW()) = YEAR(created_at)) ", nativeQuery = true)
    List<Member> findCurrentYearlyUser();
}
