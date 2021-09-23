package com.report.service;

import com.report.controller.ReportGenerationController;
import com.report.dao.ConstructionRecord;
import com.report.dao.ConstructionRecordDao;
import com.report.bo.ConstructionRecordBO;
import com.report.exception.DataFormatException;
import com.report.exception.RecordNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to compose {@link ConstructionRecordBO} object. Take input as {@link ConstructionRecord}
 * and validate mandatory field customerId,contractId,geoZone and buildDuration
 */
public class ConstructionRecordMapper {
    private static final Logger LOGGER = Logger.getLogger(ReportGenerationController.class);

    private ConstructionRecordDao constructionRecordDao;

    public ConstructionRecordMapper(ConstructionRecordDao constructionRecordDao) {
        this.constructionRecordDao = constructionRecordDao;
    }

    public List<ConstructionRecordBO> getConstructionData() throws RecordNotFoundException {
        List<ConstructionRecordBO> records = constructionRecordDao.listConstructionData()
                .stream()
                .map(this::buildDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (records == null || records.isEmpty()) {
            throw new RecordNotFoundException("No record fullfulling criteria of report generation");
        }
        return records;
    }

    private ConstructionRecordBO buildDTO(ConstructionRecord line) {
        try {
            ConstructionRecordBO dto = new ConstructionRecordBO();
            setCustomerId(dto, line.getCustomerId());
            setContractId(dto, line.getContractId());
            setGeoZone(dto, line.getGeoZone());
            setTeamCode(dto, line.getTeamCode());
            setProjectCode(dto, line.getProjectCode());
            setBuildDuration(dto, line.getBuildDuration());
            return dto;
        } catch (NumberFormatException e) {
            LOGGER.error("customerId,contractId or builduration is not numeric for :" + line);
        } catch (DataFormatException e) {
            LOGGER.error("Invalid data :" + e.getMessage() + " for " + line);
        } catch (Exception e) {
            LOGGER.error("Unknown exception occured while building construction dto :" + line);
        }
        return null;
    }

    private void setCustomerId(ConstructionRecordBO dto, String customerId) throws DataFormatException {
        if (StringUtils.isEmpty(customerId)) {
            throw new DataFormatException("customerId is either empty or null");
        }
        dto.setCustomerId(Integer.parseInt(customerId));
    }

    private void setContractId(ConstructionRecordBO dto, String contractId) throws DataFormatException {
        if (StringUtils.isEmpty(contractId)) {
            throw new DataFormatException("contractId is either empty or null");
        }
        dto.setContractId(Integer.parseInt(contractId));
    }

    private void setGeoZone(ConstructionRecordBO dto, String geoZone) throws DataFormatException {
        if (StringUtils.isEmpty(geoZone)) {
            throw new DataFormatException("geoZone is either empty or null");
        }
        dto.setGeoZone(geoZone);
    }

    private void setTeamCode(ConstructionRecordBO dto, String teamCode) throws DataFormatException {
        dto.setTeamCode(teamCode);
    }

    private void setProjectCode(ConstructionRecordBO dto, String projectCode) {
        dto.setProjectCode(projectCode);
    }

    private void setBuildDuration(ConstructionRecordBO dto, String buildDuration) throws DataFormatException {
        if (StringUtils.isEmpty(buildDuration)) {
            throw new DataFormatException("buildDuration is either empty or null");
        }
        dto.setBuildDuration(Integer.parseInt(buildDuration.replace('s', ' ').trim()));
    }

}
