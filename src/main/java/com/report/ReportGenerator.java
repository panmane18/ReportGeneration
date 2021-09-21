package com.report;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReportGenerator {
    Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionDTO> dto);

    Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionDTO> dto);

    Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionDTO> dto);

    Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionDTO> dto);
}
