package com.report;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringParser {
    public List<ConstructionDTO> parseToDTO(String input) {
        return Arrays.asList(input.split("\\n"))
                .stream()
                .map(this::buildDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private ConstructionDTO buildDTO(String line) {
        try {
            ConstructionDTO dto = new ConstructionDTO();
            String[] split = line.split(",");
            setCustomerId(dto, split[0]);
            setContractId(dto, split[1]);
            setGeoZone(dto, split[2]);
            setTeamCode(dto, split[3]);
            setProjectCode(dto, split[4]);
            setBuildDuration(dto, split[5]);
            return dto;
        } catch (NumberFormatException e) {

        } catch (Exception e) {

        }
        return null;
    }

    private void setCustomerId(ConstructionDTO dto, String customerId) throws DataFormatException {
        if (StringUtils.isEmpty(customerId)) {
            throw new DataFormatException("customerId is either empty or null");
        }
        dto.setCustomerId(Integer.parseInt(customerId));
    }

    private void setContractId(ConstructionDTO dto, String contractId) throws DataFormatException {
        if (StringUtils.isEmpty(contractId)) {
            throw new DataFormatException("contractId is either empty or null");
        }
        dto.setContractId(Integer.parseInt(contractId));
    }

    private void setGeoZone(ConstructionDTO dto, String geoZone) throws DataFormatException {
        if (StringUtils.isEmpty(geoZone)) {
            throw new DataFormatException("geoZone is either empty or null");
        }
        dto.setGeoZone(geoZone);
    }

    private void setTeamCode(ConstructionDTO dto, String teamCode) throws DataFormatException {
        dto.setTeamCode(teamCode);
    }

    private void setProjectCode(ConstructionDTO dto, String projectCode) {
        dto.setProjectCode(projectCode);
    }

    private void setBuildDuration(ConstructionDTO dto, String buildDuration) throws DataFormatException {
        if (StringUtils.isEmpty(buildDuration)) {
            throw new DataFormatException("buildDuration is either empty or null");
        }
        dto.setBuildDuration(Integer.parseInt(buildDuration.replace('s',' ').trim()));
    }

}
