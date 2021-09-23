package com.report.controller;

import com.report.exception.RecordNotFoundException;
import com.report.bo.ConstructionRecordBO;
import com.report.service.ReportGeneratorService;
import com.report.service.ConstructionRecordMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportGenerationController {
    private static final Logger LOGGER = Logger.getLogger(ReportGenerationController.class);

    ConstructionRecordMapper mapper;
    ReportGeneratorService reportGeneratorService;

    public ReportGenerationController(ConstructionRecordMapper parser, ReportGeneratorService reportGenerator) {
        this.mapper = parser;
        this.reportGeneratorService = reportGenerator;
    }

    public void generateReport() {
        try {
            List<ConstructionRecordBO> constructionRecordBOS = mapper.getConstructionData();
            LOGGER.info("--------------The number of unique customerId for each contractId.-----------------");
            Map<Integer, Integer> uniqueCountOfCustomerIdForContractId = reportGeneratorService.getUniqueCountOfCustomerIdForContractId(constructionRecordBOS);
            LOGGER.info("contractId" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForContractId.forEach((k, v) -> LOGGER.info(k + "          " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("-------------- The number of unique customerId for each geozone.-----------------");
            Map<String, Integer> uniqueCountOfCustomerIdForGeoZone = reportGeneratorService.getUniqueCountOfCustomerIdForGeoZone(constructionRecordBOS);
            LOGGER.info("GeoZone" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForGeoZone.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("-------------- The average buildduration for each geozone.----------------");
            Map<String, Double> avgBuildDurationForGeoZone = reportGeneratorService.getAvgBuildDurationForGeoZone(constructionRecordBOS);
            LOGGER.info("GeoZone" + "  " + "avgBuildDuration");
            avgBuildDurationForGeoZone.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");

            LOGGER.info("--------------The list of unique customerId for each geozone.-----------------");
            Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone = reportGeneratorService.listUniqueCustomerIdForGeoZone(constructionRecordBOS);
            LOGGER.info("GeoZone" + "  " + "uniqueCustomerIdList");
            listUniqueCustomerIdForGeoZone.forEach((k, v) -> LOGGER.info(k + "   " + v));
            LOGGER.info("------------------------------------------------------------------------------------");
        } catch (RecordNotFoundException e) {
            LOGGER.error("No record found to generate report :" + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Unknown exception occured while processing request :" + e.getMessage(), e);
        }

    }
}
