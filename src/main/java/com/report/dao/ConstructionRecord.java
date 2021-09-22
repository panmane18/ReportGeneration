package com.report.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ConstructionRecord {
    private String customerId;
    private String contractId;
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private String buildDuration;
}
