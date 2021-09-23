package com.report.dao;

import com.report.exception.RecordNotFoundException;

import java.util.List;


/**
 * Read construction data from StrigData which is comma separated values of rows
 * In future can be extended to read it from sql or mysql databases
 */
public interface ConstructionRecordDao {
    public List<ConstructionRecord> listConstructionData() throws RecordNotFoundException;
}
