package com.report.service;

import com.report.dto.ConstructionRecordDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Take {@link ConstructionRecordDTO} as input and generate following type of report
 * The number of unique customerId for each contractId.
 * The number of unique customerId for each geozone.
 * The average buildduration for each geozone.
 * The list of unique customerId for each geozone."
 */

public class ReportGeneratorService implements ReportGeneratorServiceImpl {


    @Override
    public Map<Integer, Integer> getUniqueCountOfCustomerIdForContractId(List<ConstructionRecordDTO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionRecordDTO::getContractId,
                        Collectors.mapping(ConstructionRecordDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Integer> getUniqueCountOfCustomerIdForGeoZone(List<ConstructionRecordDTO> dtos) {
        return dtos.stream()
                .collect(Collectors.groupingBy(ConstructionRecordDTO::getGeoZone,
                        Collectors.mapping(ConstructionRecordDTO::getCustomerId,
                                Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    @Override
    public Map<String, Double> getAvgBuildDurationForGeoZone(List<ConstructionRecordDTO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionRecordDTO::getGeoZone, Collectors.averagingInt(ConstructionRecordDTO::getBuildDuration))
        );
    }

    @Override
    public Map<String, Set<Integer>> listUniqueCustomerIdForGeoZone(List<ConstructionRecordDTO> dtos) {
        return dtos.stream().collect(
                Collectors.groupingBy(ConstructionRecordDTO::getGeoZone,
                        Collectors.mapping(ConstructionRecordDTO::getCustomerId, Collectors.toSet()))
        );
    }
}
