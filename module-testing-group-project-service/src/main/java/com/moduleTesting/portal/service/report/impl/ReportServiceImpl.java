package com.moduleTesting.portal.service.report.impl;

import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.entity.ReportEntity;
import com.moduleTesting.portal.entity.TaskEntity;
import com.moduleTesting.portal.repository.ReportRepository;
import com.moduleTesting.portal.repository.TaskRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.report.ReportService;
import exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.moduleTesting.portal.consts.Common.MSG_ERR_TASK_NOT_FOUND;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<ReportDto> findAll() {
        return reportRepository.findAll().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> getReportsByTaskId(Integer taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(MSG_ERR_TASK_NOT_FOUND));
        return taskEntity.getReports().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ReportDto> createReport(Integer taskId, ReportDto reportDto) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(MSG_ERR_TASK_NOT_FOUND));;
        taskEntity.getReports().add(new ReportEntity(reportDto.getDeparture(), taskEntity.getWeight(), reportDto.getDistance(),
            reportDto.getArrival()));
        taskEntity = taskRepository.save(taskEntity);
        return taskEntity.getReports().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
    }

}
