package com.moduleTesting.portal.service.report.impl;

import com.moduleTesting.portal.entity.ReportEntity;
import com.moduleTesting.portal.repository.ReportRepository;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<ReportEntity> findAll() {
        return reportRepository.findAll();
    }
}
