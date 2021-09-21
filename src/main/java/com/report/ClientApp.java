package com.report;

import com.report.controller.ReportGenerationController;
import com.report.service.ReportGeneratorService;
import com.report.utility.StringDataParser;

public class ClientApp {
    public static void main(String[] args) {
        ReportGenerationController controller = new ReportGenerationController(new StringDataParser(), new ReportGeneratorService());
        String str = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        controller.generateReport(str);
    }
}
