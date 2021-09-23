package com.report;


import com.report.bo.ConstructionRecordBO;
import com.report.dao.ConstructionRecord;
import com.report.dao.ConstructionRecordDao;
import com.report.exception.RecordNotFoundException;
import com.report.service.ConstructionRecordMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StringDataParserTest {
    ConstructionRecordMapper parser;
    ConstructionRecordDao dao;

    @Before
    public void beforeEachTest() {
        dao = mock(ConstructionRecordDao.class);
        parser = new ConstructionRecordMapper(dao);
    }

    @Test(expected = RecordNotFoundException.class)
    public void testWhenCustomerIdIsNullAndContractIdIsEmpty() throws RecordNotFoundException {
        ConstructionRecord record1 = ConstructionRecord.builder().customerId(null).contractId("2345").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("3445s").build();

        ConstructionRecord record2 = ConstructionRecord.builder().customerId("2343225").contractId("").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("3445s").build();

        List<ConstructionRecord> list = List.of(record1, record2);
        when(dao.listConstructionData()).thenReturn(list);
        parser.getConstructionData();
    }

    @Test
    public void testSuccessfullParsingOfData() throws RecordNotFoundException {
        ConstructionRecord record1 = ConstructionRecord.builder().customerId("2343225").contractId("2345").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("3445s").build();

        ConstructionRecord record2 = ConstructionRecord.builder().customerId("2343225").contractId("2345").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("3445s").build();
        List<ConstructionRecord> list = List.of(record1, record2);

        when(dao.listConstructionData()).thenReturn(list);
        List<ConstructionRecordBO> constructionDTOS = parser.getConstructionData();
        assertEquals(2, constructionDTOS.size());
    }


    @Test
    public void testBuildDurationIsNotNumeric() throws RecordNotFoundException {
        ConstructionRecord record1 = ConstructionRecord.builder().customerId("2343225").contractId("2345").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("abc").build();

        ConstructionRecord record2 = ConstructionRecord.builder().customerId("2343225").contractId("2345").geoZone("us_east")
                .teamCode("RedTeam").projectCode("ProjectApple").buildDuration("3445s").build();

        List<ConstructionRecord> list = List.of(record1, record2);
        when(dao.listConstructionData()).thenReturn(list);
        List<ConstructionRecordBO> constructionDTOS = parser.getConstructionData();
        assertEquals(1, constructionDTOS.size());
    }
}
