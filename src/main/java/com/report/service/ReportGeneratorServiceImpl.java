package com.report.service;

import com.report.bo.ConstructionRecordBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReportGeneratorServiceImpl {
    Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionRecordBO> dtos);

    Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionRecordBO> dtos);

    Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionRecordBO> dtos);

    Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionRecordBO> dtos);
}
