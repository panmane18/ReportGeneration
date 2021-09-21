package com.report.controller;

import com.report.utility.StringDataParser;
import com.report.dto.ConstructionDTO;
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
        List<ConstructionDTO> dtos = parser.parseToDTO(input);
        System.out.println(dtos);
        System.out.println("--------------The number of unique customerId for each contractId.-----------------");
        Map<Integer, Integer> uniqueCountOfCustomerIdForContractId = reportGenerator.getUniqueCountOfCustomerIdForContractId(dtos);
        System.out.println("contractId"+"  "+"uniqueCustomerId");
        uniqueCountOfCustomerIdForContractId.forEach((k, v) -> System.out.println(k + "          " + v));
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------- The number of unique customerId for each geozone.-----------------");
        Map<String, Integer> uniqueCountOfCustomerIdForGeoZone = reportGenerator.getUniqueCountOfCustomerIdForGeoZone(dtos);
        uniqueCountOfCustomerIdForGeoZone.forEach((k, v) -> System.out.println(k + "   " + v));
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("-------------- The average buildduration for each geozone.----------------");
        Map<String, Double> avgBuildDurationForGeoZone = reportGenerator.getAvgBuildDurationForGeoZone(dtos);
        avgBuildDurationForGeoZone.forEach((k, v) -> System.out.println(k + "   " + v));
        System.out.println("------------------------------------------------------------------------------------");

        System.out.println("--------------The list of unique customerId for each geozone.-----------------");
        Map<String, Set<Integer>> stringSetMap = reportGenerator.listUniqueCustomerIdForGeoZone(dtos);
        stringSetMap.forEach((k, v) -> System.out.println(k + "   " + v));
        System.out.println("------------------------------------------------------------------------------------");

    }
}
