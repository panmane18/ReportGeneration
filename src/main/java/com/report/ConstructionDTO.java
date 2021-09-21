package com.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConstructionDTO {
    private int customerId;
    private int contractId;
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private int buildDuration;
}
