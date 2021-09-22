package com.report;

import com.report.controller.ReportGenerationController;
import com.report.dao.StringConstructionRecordDaoImpl;
import com.report.service.ReportGeneratorService;
import com.report.service.StringDataParser;

public class ClientApp {

    public static void main(String[] args) {
        ReportGenerationController controller = new ReportGenerationController(new StringDataParser(new StringConstructionRecordDaoImpl()), new ReportGeneratorService());
        controller.generateReport();
    }
}
