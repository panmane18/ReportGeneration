package com.report.dao;

import com.report.exception.RecordNotFoundException;

import java.util.List;

public interface ConstructionRecordDao {
    public List<ConstructionRecord> listConstructionData() throws RecordNotFoundException;
}
