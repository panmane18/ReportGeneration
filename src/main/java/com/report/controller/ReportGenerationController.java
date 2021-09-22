package com.report.controller;

import com.report.ClientApp;
import com.report.exception.RecordNotFoundException;
import com.report.bo.ConstructionRecordBO;
import com.report.service.ReportGeneratorServiceImpl;
import com.report.service.StringDataParser;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportGenerationController {
    private static final Logger LOGGER = Logger.getLogger(ReportGenerationController.class);

    StringDataParser parser;
    ReportGeneratorServiceImpl reportGenerator;

    public ReportGenerationController(StringDataParser parser, ReportGeneratorServiceImpl reportGenerator) {
        this.parser = parser;
        this.reportGenerator = reportGenerator;
    }

    public void generateReport() {
        try {
            List<ConstructionRecordBO> dtos = parser.getConstructionData();
            LOGGER.info("--------------The number of unique customerId for each contractId.-----------------");
            Map<Integer, Integer> uniqueCountOfCustomerIdForContractId = reportGenerator.getUniqueCountOfCustomerIdForContractId(dtos);
            LOGGER.info("contractId" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForContractId.forEach((k, v) -> LOGGER.info(k + "          " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("-------------- The number of unique customerId for each geozone.-----------------");
            Map<String, Integer> uniqueCountOfCustomerIdForGeoZone = reportGenerator.getUniqueCountOfCustomerIdForGeoZone(dtos);
            LOGGER.info("GeoZone" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForGeoZone.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("-------------- The average buildduration for each geozone.----------------");
            Map<String, Double> avgBuildDurationForGeoZone = reportGenerator.getAvgBuildDurationForGeoZone(dtos);
            LOGGER.info("GeoZone" + "  " + "avgBuildDuration");
            avgBuildDurationForGeoZone.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("--------------The list of unique customerId for each geozone.-----------------");
            Map<String, Set<Integer>> stringSetMap = reportGenerator.listUniqueCustomerIdForGeoZone(dtos);
            LOGGER.info("GeoZone" + "  " + "uniqueCustomerIdList");
            stringSetMap.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");
        } catch (RecordNotFoundException e) {
            LOGGER.error("No record found to generate report :" + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Unknown exception occured while processing request :" + e.getMessage(), e);
        }

    }
}
