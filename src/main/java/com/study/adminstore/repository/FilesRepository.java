package com.study.adminstore.repository;

import com.study.adminstore.model.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository  extends JpaRepository<Files, Long> {
}
