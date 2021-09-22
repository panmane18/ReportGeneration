package com.report;

import com.report.dto.ConstructionRecordDTO;
import com.report.service.ReportGeneratorService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ReportGeneratorServiceTest {

    static ReportGeneratorService reportGeneratore;
    static List<ConstructionRecordDTO> dtos = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        reportGeneratore = new ReportGeneratorService();
        dtos.add(new ConstructionRecordDTO(2343225, 2345, "us_east", "RedTeam", "ProjectApple", 3445));
        dtos.add(new ConstructionRecordDTO(1223456, 2345, "us_west", "BlueTeam", "ProjectBanana", 2211));
        dtos.add(new ConstructionRecordDTO(3244332, 2346, "eu_west", "YellowTeam3", "ProjectCarrot", 4322));
        dtos.add(new ConstructionRecordDTO(1233456, 2345, "us_west", "BlueTeam", "ProjectDate", 2221));
        dtos.add(new ConstructionRecordDTO(3244132, 2346, "eu_west", "YellowTeam3", "ProjectEgg", 4122));
    }

    @Test
    public void testListUniqueCustomerIdForGeoZone() {
        Map<String, Set<Integer>> result = reportGeneratore.listUniqueCustomerIdForGeoZone(dtos);
        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{2343225}, result.get("us_east").toArray());
        assertArrayEquals(new Integer[]{1223456, 1233456}, result.get("us_west").toArray());
        assertArrayEquals(new Integer[]{3244132, 3244332}, result.get("eu_west").toArray());
    }

    @Test
    public void testGetAvgBuildDurationForGeoZone() {
        Map<String, Double> result = reportGeneratore.getAvgBuildDurationForGeoZone(dtos);
        assertEquals(3, result.size());
        assertEquals(3445, result.get("us_east").intValue());
        assertEquals(2216, result.get("us_west").intValue());
        assertEquals(4222, result.get("eu_west").intValue());
        ;
    }

    @Test
    public void testGetUniqueCountOfCustomerIdForContractId() {
        Map<Integer, Integer> result = reportGeneratore.getUniqueCountOfCustomerIdForContractId(dtos);
        assertEquals(2, result.size());
        assertEquals(3, result.get(2345).intValue());
        assertEquals(2, result.get(2346).intValue());
    }

    @Test
    public void testGetUniqueCountOfCustomerIdForGeoZone() {
        Map<String, Integer> result = reportGeneratore.getUniqueCountOfCustomerIdForGeoZone(dtos);
        assertEquals(3, result.size());
        assertEquals(1, result.get("us_east").intValue());
        assertEquals(2, result.get("us_west").intValue());
        assertEquals(2, result.get("eu_west").intValue());
    }
}
