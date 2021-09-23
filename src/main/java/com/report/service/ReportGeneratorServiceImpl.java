package com.report.service;

import com.report.bo.ConstructionRecordBO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportGeneratorServiceImpl implements ReportGeneratorService {


    @Override
    public Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionRecordBO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionRecordBO::getContractId,
                        Collectors.mapping(ConstructionRecordBO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionRecordBO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionRecordBO::getGeoZone,
                        Collectors.mapping(ConstructionRecordBO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionRecordBO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionRecordBO::getGeoZone, Collectors.averagingInt(ConstructionRecordBO::getBuildDuration))
        );
    }

    @Override
    public Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionRecordBO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionRecordBO::getGeoZone,
                        Collectors.mapping(ConstructionRecordBO::getCustomerId, Collectors.toSet()))
        );
    }
}
