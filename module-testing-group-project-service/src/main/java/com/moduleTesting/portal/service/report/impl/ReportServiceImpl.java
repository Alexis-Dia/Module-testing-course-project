package com.moduleTesting.portal.service.report.impl;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.repository.ReportRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<ReportDto> findAll() {
        return reportRepository.findAll().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> getReportsByTaskId(Integer taskId) {
        return null;
    }

    @Override
    public List<ReportDto> createReport(Date departure, Float weight, Float distance, Date arrival) {
        return null;
    }

}
