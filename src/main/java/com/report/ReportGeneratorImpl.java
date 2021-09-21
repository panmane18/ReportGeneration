package com.report;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportGeneratorImpl implements ReportGenerator {


    @Override
    public Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionDTO> constructionDTOS) {
        return constructionDTOS.stream()
                .collect(Collectors.groupingBy(ConstructionDTO::getContractId,
                        Collectors.mapping(ConstructionDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionDTO> constructionDTOS) {
        return constructionDTOS.stream()
                .collect(Collectors.groupingBy(ConstructionDTO::getGeoZone,
                        Collectors.mapping(ConstructionDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionDTO> constructionDTOS) {
        return constructionDTOS.stream().collect(
                Collectors.groupingBy(ConstructionDTO::getGeoZone, Collectors.averagingInt(ConstructionDTO::getBuildDuration))
        );
    }

    @Override
    public Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionDTO> constructionDTOS) {
        return constructionDTOS.stream().collect(
                Collectors.groupingBy(ConstructionDTO::getGeoZone,
                        Collectors.mapping(ConstructionDTO::getCustomerId, Collectors.toSet()))
        );
    }
}
