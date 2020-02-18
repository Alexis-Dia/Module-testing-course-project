package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return allReports;
    }

    @PostMapping("/getByTaskId")
    public List<ReportDto> getReportsByTaskId(@RequestParam("taskId") Integer taskId) {

        final List<ReportDto> reports = reportService.getReportsByTaskId(taskId);

        return reports;
    }

    @PostMapping("/createReport")
    public List<ReportDto> createReport(Date departure, Float weight, Float distance, Date arrival) {

        final List<ReportDto> reports = reportService.createReport(departure, weight, distance, arrival);

        return reports;
    }
}
