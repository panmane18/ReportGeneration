package com.report;

import com.report.controller.ReportGenerationController;
import com.report.dao.StringConstructionRecordDaoImpl;
import com.report.service.ReportGeneratorServiceImpl;
import com.report.service.ConstructionRecordMapper;

public class ClientApp {

    public static void main(String[] args) {
        ReportGenerationController controller = new ReportGenerationController(new ConstructionRecordMapper(new StringConstructionRecordDaoImpl()), new ReportGeneratorServiceImpl());
        controller.generateReport();
    }
}
