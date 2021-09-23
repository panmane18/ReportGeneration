package com.report.service;

import com.report.bo.ConstructionRecordBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Take {@link ConstructionRecordBO} as input and generate following type of report
 * The number of unique customerId for each contractId.
 * The number of unique customerId for each geozone.
 * The average buildduration for each geozone.
 * The list of unique customerId for each geozone."
 */

public interface ReportGeneratorService {
    Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionRecordBO> dtos);

    Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionRecordBO> dtos);

    Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionRecordBO> dtos);

    Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionRecordBO> dtos);
}
