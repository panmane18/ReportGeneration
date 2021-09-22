package com.report.dao;


import com.report.exception.RecordNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringConstructionRecordDaoImpl implements ConstructionRecordDao {
    @Override
    public List<ConstructionRecord> listConstructionData() throws RecordNotFoundException{
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecord> records = Arrays.asList(str.split("\\n"))
                .stream()
                .map(this::mapToObject)
                .collect(Collectors.toList());
        if (records == null || records.isEmpty())
            throw new RecordNotFoundException("Record not present to generate report");
        return records;
    }

    private ConstructionRecord mapToObject(String row) {
        ConstructionRecord record = new ConstructionRecord();
        String[] split = row.split(",");
        record.setCustomerId(split[0]);
        record.setContractId(split[1]);
        record.setGeoZone(split[2]);
        record.setTeamCode(split[3]);
        record.setProjectCode(split[4]);
        record.setBuildDuration(split[5]);
        return record;
    }
}
