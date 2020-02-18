package com.moduleTesting.portal.service.report;

import com.moduleTesting.portal.dto.ReportDto;

import java.util.List;

public interface ReportService {

    List<ReportDto> findAll();

    List<ReportDto> getReportsByTaskId(Integer taskId);

    List<ReportDto> createReport(Integer taskId, ReportDto reportDto);

}