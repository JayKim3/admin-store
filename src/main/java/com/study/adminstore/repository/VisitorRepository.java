package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Member;
import com.study.adminstore.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Query(value = "SELECT country FROM visitor", nativeQuery = true)
    ArrayList<String> findCurrentCountryUser();
}
