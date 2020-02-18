package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{taskId}/createReport")
    public List<ReportDto> createReport(@PathVariable("taskId") Integer taskId, @RequestBody ReportDto reportDto) {

        final List<ReportDto> reports = reportService.createReport(taskId, reportDto);

        return reports;
    }
}
