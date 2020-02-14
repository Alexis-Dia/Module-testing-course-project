package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAll();
}

