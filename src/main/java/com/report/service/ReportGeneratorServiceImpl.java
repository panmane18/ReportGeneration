package com.report.service;

import com.report.dto.ConstructionRecordDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReportGeneratorServiceImpl {
    Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionRecordDTO> dtos);

    Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionRecordDTO> dtos);

    Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionRecordDTO> dtos);

    Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionRecordDTO> dtos);
}
