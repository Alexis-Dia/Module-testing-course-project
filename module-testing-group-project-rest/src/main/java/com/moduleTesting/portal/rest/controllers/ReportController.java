package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/all")
    public List<ReportDto> getAllTasks() {

        final List<ReportDto> allReports = reportService.findAll();
        System.out.println(allReports);

        return allReports;
    }
}
