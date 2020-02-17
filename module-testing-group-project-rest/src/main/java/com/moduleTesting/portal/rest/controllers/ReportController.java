package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/all")
    public List<ReportDto> getAllReports() {

        final List<ReportDto> allReports = reportService.findAll();
        System.out.println(allReports);

        return allReports;
    }

    @PostMapping("/getByTaskId")
    public List<ReportDto> getReportsByTaskId(Integer taskId) {

        final List<ReportDto> reports = reportService.getReportsByTaskId(taskId);
        System.out.println(reports);

        return reports;
    }

    @PostMapping("/createReport")
    public List<ReportDto> createReport(Date departure, Float weight, Float distance, Date arrival) {

        final List<ReportDto> reports = reportService.createReport(departure, weight, distance, arrival);
        System.out.println(reports);

        return reports;
    }
}
