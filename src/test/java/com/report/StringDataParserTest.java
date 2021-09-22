package com.report;

import com.report.dto.ConstructionRecordDTO;
import com.report.exception.RecordNotFoundException;
import com.report.utility.StringDataParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class StringDataParserTest {
    static StringDataParser parser;

    @BeforeClass
    public static void setUp() {
        parser = new StringDataParser();
    }

    @Test(expected = RecordNotFoundException.class)
    public void testInputStringIsEmpty() throws RecordNotFoundException {
        String str = "";
        parser.parseToDTO(str);
    }

    @Test(expected = RecordNotFoundException.class)
    public void testNoRecordToProcess() throws RecordNotFoundException {
        String str = ",2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "3244132,,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
    }

    @Test
    public void testSuccessfullParsingOfData() throws RecordNotFoundException {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(5, constructionDTOS.size());
    }

    @Test
    public void testCustomerIdEmpty() throws RecordNotFoundException {
        String str = ",2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(3, constructionDTOS.size());
    }

    @Test
    public void testContractIdEmpty() throws RecordNotFoundException {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(2, constructionDTOS.size());
    }

    @Test
    public void testGeoZoneEmpty() throws RecordNotFoundException {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(4, constructionDTOS.size());
    }

    @Test
    public void testBuildDurationEmpty() throws RecordNotFoundException {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,\n" +
                "3244132,2346,,YellowTeam3,ProjectEgg,";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(3, constructionDTOS.size());
    }

    @Test
    public void testBuildDurationIsNotNumeric() throws RecordNotFoundException {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,us_west,YellowTeam3,ProjectEgg,abc";
        List<ConstructionRecordDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(4, constructionDTOS.size());
    }

}
