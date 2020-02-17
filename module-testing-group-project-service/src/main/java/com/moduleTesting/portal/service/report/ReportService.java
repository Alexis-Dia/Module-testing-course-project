package com.moduleTesting.portal.service.report;

import com.moduleTesting.portal.entity.ReportEntity;

import java.util.Date;
import java.util.List;

public interface ReportService {

    List<ReportEntity> findAll();

    List<ReportEntity> getReportsByTaskId(Integer taskId);

    void createReport(Date departure, Float weight, Float distance, Date arrival);

}