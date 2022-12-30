package com.codingboot.repository;

import com.codingboot.model.ReportTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportTableRepository extends JpaRepository<ReportTable, Integer> {
    ReportTable getById(Integer id);
}