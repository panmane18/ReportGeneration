package com.report;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class StringParserTest {
    static StringParser parser;

    @BeforeClass
    public static void setUp() {
        parser = new StringParser();
    }

    @Test
    public void testSuccessfullParsingOfData() {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(5, constructionDTOS.size());
    }

    @Test
    public void testCustomerIdEmpty() {
        String str = ",2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(3, constructionDTOS.size());
    }

    @Test
    public void testContractIdEmpty() {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(2, constructionDTOS.size());
    }

    @Test
    public void testGeoZoneEmpty() {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,,YellowTeam3,ProjectEgg,4122s";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(4, constructionDTOS.size());
    }

    @Test
    public void testBuildDurationEmpty() {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,\n" +
                "3244132,2346,,YellowTeam3,ProjectEgg,";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(3, constructionDTOS.size());
    }

    @Test
    public void testBuildDurationIsNotNumeric() {
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,,YellowTeam3,ProjectEgg,abc";
        List<ConstructionDTO> constructionDTOS = parser.parseToDTO(str);
        Assert.assertEquals(4, constructionDTOS.size());
    }

}
