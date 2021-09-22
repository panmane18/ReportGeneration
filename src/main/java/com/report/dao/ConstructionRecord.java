package com.report.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

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
