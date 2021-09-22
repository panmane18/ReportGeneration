package com.report.utility;

import com.report.dto.ConstructionRecordDTO;
import com.report.exception.DataFormatException;
import com.report.exception.RecordNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to compose {@link ConstructionRecordDTO} object. Take comma separated string data as input row and
 * build ConstructionRecordDTO object
 */
public class StringDataParser {
    public List<ConstructionRecordDTO> parseToDTO(String input) throws RecordNotFoundException {
        if (StringUtils.isEmpty(input)) {
            throw new RecordNotFoundException("No record found to parse to dto");
        }
        List<ConstructionRecordDTO> records = Arrays.asList(input.split("\\n"))
                .stream()
                .map(this::buildDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (records == null || records.isEmpty()) {
            throw new RecordNotFoundException("Mandatory fields are missing ");
        }
        return records;
    }

    private ConstructionRecordDTO buildDTO(String line) {
        try {
            ConstructionRecordDTO dto = new ConstructionRecordDTO();
            String[] split = line.split(",");
            setCustomerId(dto, split[0]);
            setContractId(dto, split[1]);
            setGeoZone(dto, split[2]);
            setTeamCode(dto, split[3]);
            setProjectCode(dto, split[4]);
            setBuildDuration(dto, split[5]);
            return dto;
        } catch (NumberFormatException e) {
            System.out.println("customerId,contractId or builduration is not numeric for :" + line);
        } catch (DataFormatException e){
            System.out.println("Invalid data :"+e.getMessage()+" for "+line);
        }
        catch (Exception e) {
            System.out.println("Unknown exception occured while building construction dto :" + line);
        }
        return null;
    }

    private void setCustomerId(ConstructionRecordDTO dto, String customerId) throws DataFormatException {
        if (StringUtils.isEmpty(customerId)) {
            throw new DataFormatException("customerId is either empty or null");
        }
        dto.setCustomerId(Integer.parseInt(customerId));
    }

    private void setContractId(ConstructionRecordDTO dto, String contractId) throws DataFormatException {
        if (StringUtils.isEmpty(contractId)) {
            throw new DataFormatException("contractId is either empty or null");
        }
        dto.setContractId(Integer.parseInt(contractId));
    }

    private void setGeoZone(ConstructionRecordDTO dto, String geoZone) throws DataFormatException {
        if (StringUtils.isEmpty(geoZone)) {
            throw new DataFormatException("geoZone is either empty or null");
        }
        dto.setGeoZone(geoZone);
    }

    private void setTeamCode(ConstructionRecordDTO dto, String teamCode) throws DataFormatException {
        dto.setTeamCode(teamCode);
    }

    private void setProjectCode(ConstructionRecordDTO dto, String projectCode) {
        dto.setProjectCode(projectCode);
    }

    private void setBuildDuration(ConstructionRecordDTO dto, String buildDuration) throws DataFormatException {
        if (StringUtils.isEmpty(buildDuration)) {
            throw new DataFormatException("buildDuration is either empty or null");
        }
        dto.setBuildDuration(Integer.parseInt(buildDuration.replace('s', ' ').trim()));
    }

}
