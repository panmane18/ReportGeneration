package com.report.service;

import com.report.dto.ConstructionDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportGeneratorService implements ReportGeneratorServiceImpl {


    @Override
    public Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionDTO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionDTO::getContractId,
                        Collectors.mapping(ConstructionDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionDTO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionDTO::getGeoZone,
                        Collectors.mapping(ConstructionDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionDTO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionDTO::getGeoZone, Collectors.averagingInt(ConstructionDTO::getBuildDuration))
        );
    }

    @Override
    public Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionDTO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionDTO::getGeoZone,
                        Collectors.mapping(ConstructionDTO::getCustomerId, Collectors.toSet()))
        );
    }
}
