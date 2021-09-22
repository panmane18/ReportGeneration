package com.report.controller;

import com.report.exception.RecordNotFoundException;
import com.report.utility.StringDataParser;
import com.report.dto.ConstructionRecordDTO;
import com.report.service.ReportGeneratorServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportGenerationController {
    StringDataParser parser;
    ReportGeneratorServiceImpl reportGenerator;

    public ReportGenerationController(StringDataParser parser, ReportGeneratorServiceImpl reportGenerator) {
        this.parser = parser;
        this.reportGenerator = reportGenerator;
    }

    public void generateReport(String input) {
        try {
            List<ConstructionRecordDTO> dtos = parser.parseToDTO(input);
            System.out.println(dtos);
            System.out.println("--------------The number of unique customerId for each contractId.-----------------");
            Map<Integer, Integer> uniqueCountOfCustomerIdForContractId = reportGenerator.getUniqueCountOfCustomerIdForContractId(dtos);
            System.out.println("contractId" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForContractId.forEach((k, v) -> System.out.println(k + "          " + v));
            System.out.println("------------------------------------------------------------------------------------");

            System.out.println("-------------- The number of unique customerId for each geozone.-----------------");
            Map<String, Integer> uniqueCountOfCustomerIdForGeoZone = reportGenerator.getUniqueCountOfCustomerIdForGeoZone(dtos);
            System.out.println("GeoZone" + "  " + "uniqueCustomerId");
            uniqueCountOfCustomerIdForGeoZone.forEach((k, v) -> System.out.println(k + "   " + v));
            System.out.println("------------------------------------------------------------------------------------");

            System.out.println("-------------- The average buildduration for each geozone.----------------");
            Map<String, Double> avgBuildDurationForGeoZone = reportGenerator.getAvgBuildDurationForGeoZone(dtos);
            System.out.println("GeoZone" + "  " + "avgBuildDuration");
            avgBuildDurationForGeoZone.forEach((k, v) -> System.out.println(k + "   " + v));
            System.out.println("------------------------------------------------------------------------------------");

            System.out.println("--------------The list of unique customerId for each geozone.-----------------");
            Map<String, Set<Integer>> stringSetMap = reportGenerator.listUniqueCustomerIdForGeoZone(dtos);
            System.out.println("GeoZone" + "  " + "uniqueCustomerIdList");
            stringSetMap.forEach((k, v) -> System.out.println(k + "   " + v));
            System.out.println("------------------------------------------------------------------------------------");
        } catch (RecordNotFoundException e) {
            System.out.println("No record found to generate report :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown exception occured while processing request :" + e.getMessage());
        }

    }
}
