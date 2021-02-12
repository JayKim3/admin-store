package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
