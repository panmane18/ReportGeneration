package com.report;

import com.report.bo.ConstructionRecordBO;
import com.report.service.ReportGeneratorServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ReportGeneratorServiceTest {

    static ReportGeneratorServiceImpl reportGeneratorService;
    static List<ConstructionRecordBO> bos;

    @BeforeClass
    public static void setUp() {
        reportGeneratorService = new ReportGeneratorServiceImpl();
        ConstructionRecordBO record1 = ConstructionRecordBO.builder().customerId(2343225).contractId(2345)
                .geoZone("us_east").teamCode("RedTeam").projectCode("ProjectApple").buildDuration(3445).build();

        ConstructionRecordBO record2 = ConstructionRecordBO.builder().customerId(1223456).contractId(2345)
                .geoZone("us_west").teamCode("BlueTeam").projectCode("ProjectBanana").buildDuration(2211).build();

        ConstructionRecordBO record3 = ConstructionRecordBO.builder().customerId(3244332).contractId(2346)
                .geoZone("eu_west").teamCode("YellowTeam3").projectCode("ProjectCarrot").buildDuration(4322).build();

        ConstructionRecordBO record4 = ConstructionRecordBO.builder().customerId(1233456).contractId(2345)
                .geoZone("us_west").teamCode("BlueTeam").projectCode("ProjectDate").buildDuration(2221).build();

        ConstructionRecordBO record5 = ConstructionRecordBO.builder().customerId(3244132).contractId(2346)
                .geoZone("eu_west").teamCode("YellowTeam3").projectCode("ProjectEgg").buildDuration(4122).build();

        bos = List.of(record1, record2, record3, record4, record5);
    }

    @Test
    public void testListUniqueCustomerIdForGeoZone() {
        Map<String, Set<Integer>> result = reportGeneratorService.listUniqueCustomerIdForGeoZone(bos);
        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{2343225}, result.get("us_east").toArray());
        assertArrayEquals(new Integer[]{1223456, 1233456}, result.get("us_west").toArray());
        assertArrayEquals(new Integer[]{3244132, 3244332}, result.get("eu_west").toArray());
    }

    @Test
    public void testGetAvgBuildDurationForGeoZone() {
        Map<String, Double> result = reportGeneratorService.getAvgBuildDurationForGeoZone(bos);
        assertEquals(3, result.size());
        assertEquals(3445, result.get("us_east").intValue());
        assertEquals(2216, result.get("us_west").intValue());
        assertEquals(4222, result.get("eu_west").intValue());
    }

    @Test
    public void testGetUniqueCountOfCustomerIdForContractId() {
        Map<Integer, Integer> result = reportGeneratorService.getUniqueCountOfCustomerIdForContractId(bos);
        assertEquals(2, result.size());
        assertEquals(3, result.get(2345).intValue());
        assertEquals(2, result.get(2346).intValue());
    }

    @Test
    public void testGetUniqueCountOfCustomerIdForGeoZone() {
        Map<String, Integer> result = reportGeneratorService.getUniqueCountOfCustomerIdForGeoZone(bos);
        assertEquals(3, result.size());
        assertEquals(1, result.get("us_east").intValue());
        assertEquals(2, result.get("us_west").intValue());
        assertEquals(2, result.get("eu_west").intValue());
    }
}
