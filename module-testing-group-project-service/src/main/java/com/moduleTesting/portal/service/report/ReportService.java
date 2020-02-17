package com.moduleTesting.portal.service.report;

import com.moduleTesting.portal.dto.ReportDto;

import java.util.Date;
import java.util.List;

public interface ReportService {

    List<ReportDto> findAll();

    List<ReportDto> getReportsByTaskId(Integer taskId);

    void createReport(Date departure, Float weight, Float distance, Date arrival);

}