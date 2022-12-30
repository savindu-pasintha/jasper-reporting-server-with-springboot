package com.codingboot.service;

import com.codingboot.model.ReportTable;
import com.codingboot.repository.ReportTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportTableService {
    @Autowired
    private ReportTableRepository reportTableRepository;

    public ReportTable addReportTable(ReportTable reportTable){
        return reportTableRepository.save(reportTable);
    }

}
